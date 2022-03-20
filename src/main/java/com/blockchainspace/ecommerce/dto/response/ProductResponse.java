package com.blockchainspace.ecommerce.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {

    private String code;

    private String name;

    private int quantity;

    private BigDecimal price;

    private String seller;

}
