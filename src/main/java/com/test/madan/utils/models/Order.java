package com.test.madan.utils.models;

import com.test.madan.utils.models.enums.Operation;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by madan on 26/03/21.
 **/

@Data
public class Order {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    private String orderId;
    private LocalTime time;
    private String stock;
    private Operation operation;
    private Double price;
    private Long quantity;

    public Order(String orderId, String time, String stock, String operation, String price, String quantity)
    throws NumberFormatException, DateTimeParseException {
        this.orderId = orderId;
        this.time = LocalTime.parse(time, formatter);
        this.stock = stock;
        this.operation = Operation.valueOf(operation.toUpperCase());
        this.price = Double.valueOf(price);
        this.quantity = Long.valueOf(quantity);
    }
}
