package com.cart.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailInfo {
	  private String id;

	    private String productCode;
	    private String productName;

	    private int quanity;
	    private double price;
	    private double amount;
}
