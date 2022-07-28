package com.cart.shoppingapp.model;

import com.cart.shoppingapp.form.CustomerForm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerInfo {
	private String name;
    private String address;
    private String email;
    private String phone;

    private boolean valid;

    public CustomerInfo(CustomerForm customerForm) {
        this.name = customerForm.getName();
        this.address = customerForm.getAddress();
        this.email = customerForm.getEmail();
        this.phone = customerForm.getPhone();
        this.valid = customerForm.isValid();
    }
}
