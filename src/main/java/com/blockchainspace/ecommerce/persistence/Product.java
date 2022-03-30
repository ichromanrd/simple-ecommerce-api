package com.blockchainspace.ecommerce.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ichroman Raditya Duwila
 * @since 2022-03-30
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String category;

    private String publisher;

    private String unitOfMeasurement;

    private Integer quantity;

    private BigDecimal price;

    private Integer owner;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Product{" +
        "code=" + code +
        ", name=" + name +
        ", category=" + category +
        ", publisher=" + publisher +
        ", unitOfMeasurement=" + unitOfMeasurement +
        ", quantity=" + quantity +
        ", price=" + price +
        ", owner=" + owner +
        "}";
    }
}
