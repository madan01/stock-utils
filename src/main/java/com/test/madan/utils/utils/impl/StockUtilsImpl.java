package com.test.madan.utils.utils.impl;

import com.test.madan.utils.models.Order;
import com.test.madan.utils.models.Output;
import com.test.madan.utils.models.enums.Operation;
import com.test.madan.utils.utils.StockUtils;

import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Created by madan on 26/03/21.
 **/

@Singleton
public class StockUtilsImpl implements StockUtils{
    PriorityQueue<Order> sellQueue = new PriorityQueue<>(Comparator.comparing(Order::getPrice));
    PriorityQueue<Order> buyQueue =
            new PriorityQueue<>(Comparator.comparing(Order::getPrice).reversed()
                    .thenComparing(Order::getOrderId));
    public List<Output> readData(String filePath) {
        List<Output> outputList = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(orderstring ->
            {
                String[] values = orderstring.split("\\s+");
                //ignore invalid rows in the data
                if(values.length==6) {
                    try {
                        Order order = new Order(values[0], values[1], values[2], values[3], values[4], values[5]);
                        outputList.addAll(processOrder(order));
                    }catch(NumberFormatException|DateTimeParseException e){
                        // do nothing
                        // we can also push this a bad record queue
                    }
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputList;
    }

    public List<Output> processOrder(Order order) {
        List<Output> outputList = new ArrayList<>();

        if (order.getOperation().equals(Operation.SELL)) {
            sellQueue.add(order);
        } else if (order.getOperation().equals(Operation.BUY)) {
            buyQueue.add(order);
        }
        while (true) {
            Order lowestSellOrder = sellQueue.peek();
            Order highestBuyOrder = buyQueue.peek();

            //finding lowest selling order against highest buying order
            if ((!sellQueue.isEmpty()
                    && !buyQueue.isEmpty()) // proceed only if both queues are not empty
                    && lowestSellOrder.getPrice().compareTo(highestBuyOrder.getPrice()) < 0) {

                // lowest sell prices is lower than the buy price
                if (highestBuyOrder.getQuantity() > lowestSellOrder.getQuantity()) {
                    outputList.add(new Output(highestBuyOrder.getOrderId(), lowestSellOrder.getPrice(),
                            lowestSellOrder.getQuantity(), lowestSellOrder.getOrderId()));
                    highestBuyOrder.setQuantity(highestBuyOrder.getQuantity() - lowestSellOrder.getQuantity());
                    //remove the lowest sell order from the queue
                    sellQueue.remove();
                } else if (highestBuyOrder.getQuantity() < lowestSellOrder.getQuantity()) {
                    outputList.add(new Output(highestBuyOrder.getOrderId(), lowestSellOrder.getPrice(),
                            highestBuyOrder.getQuantity(), lowestSellOrder.getOrderId()));
                    lowestSellOrder.setQuantity(lowestSellOrder.getQuantity() - highestBuyOrder.getQuantity());
                    buyQueue.remove();
                } else if (highestBuyOrder.getQuantity() == lowestSellOrder.getQuantity()) {
                    buyQueue.remove();
                    sellQueue.remove();
                    outputList.add(new Output(highestBuyOrder.getOrderId(), lowestSellOrder.getPrice(),
                            highestBuyOrder.getQuantity(), lowestSellOrder.getOrderId()));
                }

            } else {
                break;
            }
        }
        return outputList;
    }

    public void resetQueue(){
       sellQueue = new PriorityQueue<>(Comparator.comparing(Order::getPrice));
       buyQueue = new PriorityQueue<>(Comparator.comparing(Order::getPrice).reversed()
                        .thenComparing(Order::getOrderId));
    }
}
