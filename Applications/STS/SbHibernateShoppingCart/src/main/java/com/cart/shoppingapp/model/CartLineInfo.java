package com.cart.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLineInfo {

	private ProductInfo productInfo;
    private int quantity;
    
    public double getAmount() {
        return this.productInfo.getPrice() * this.quantity;
    }
}
