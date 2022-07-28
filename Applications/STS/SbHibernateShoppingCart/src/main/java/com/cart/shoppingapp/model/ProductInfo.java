package com.cart.shoppingapp.model;

import com.cart.shoppingapp.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
	  	private String code;
	    private String name;
	    private double price;
	    
	
	    public ProductInfo(Product product) {
	        this.code = product.getCode();
	        this.name = product.getName();
	        this.price = product.getPrice();
	    }
	    

}
