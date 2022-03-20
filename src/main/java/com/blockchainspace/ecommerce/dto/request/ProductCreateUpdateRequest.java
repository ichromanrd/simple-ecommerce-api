package com.blockchainspace.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateUpdateRequest {

    private String code;

    private String name;

    private String group;

    private String category;

    private String publisher;

    private String uom;

    private int quantity;

    private BigDecimal price;

}
