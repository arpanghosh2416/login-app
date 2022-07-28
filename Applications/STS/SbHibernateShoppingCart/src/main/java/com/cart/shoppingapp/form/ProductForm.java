package com.cart.shoppingapp.form;

import org.springframework.web.multipart.MultipartFile;

import com.cart.shoppingapp.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductForm {
	
	 private String code;
	    private String name;
	    private double price;

	    private boolean newProduct = false;

	    // Upload file.
	    private MultipartFile fileData;

	   

	    public ProductForm(Product product) {
	    
	        this.code = product.getCode();
	        this.name = product.getName();
	        this.price = product.getPrice();
	        
	    }

}
