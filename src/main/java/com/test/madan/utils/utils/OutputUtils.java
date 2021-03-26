package com.test.madan.utils.utils;

import com.test.madan.utils.models.Output;

import java.util.List;

/**
 * Created by madan on 26/03/21.
 **/

public class OutputUtils {
    public static void printStockOutput(List<Output> output){
        output.stream().forEach(o -> System.out.println(
                o.getBuyOrderId()+"\t"+
                o.getPrice()+"\t"+
                o.getQuantity()+"\t"+
                o.getSellOrderId()));
    }
}
