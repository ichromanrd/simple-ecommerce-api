package com.blockchainspace.ecommerce;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProductManagementException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

}
