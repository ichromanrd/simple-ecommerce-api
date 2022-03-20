package com.blockchainspace.ecommerce.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Product {

    private String code;

    private String name;

    private String category;

    private String publisher;

    private String unitOfMeasurement;

    private int quantity;

    private BigDecimal price;

    private int owner;

}
