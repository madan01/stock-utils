package com.test.madan.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

/**
 * Created by madan on 26/03/21.
 **/

@Data

public class Output {
    private String buyOrderId;
    private Double price;
    private Long quantity;
    private String sellOrderId;

    public Output(String buyOrderId, Double price, Long quantity, String sellOrderId) {
        this.buyOrderId = buyOrderId;
        this.price = price;
        this.quantity = quantity;
        this.sellOrderId = sellOrderId;
    }
}
