package com.interview.shoppingbasket.model;

import lombok.Data;

@Data
public class BasketItem {
    private String productCode;
    private String productName;
    private int quantity;
    private double productRetailPrice;

}
