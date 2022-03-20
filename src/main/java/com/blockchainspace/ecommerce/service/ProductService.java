package com.blockchainspace.ecommerce.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.blockchainspace.ecommerce.ProductManagementException;
import com.blockchainspace.ecommerce.dto.request.ProductCreateUpdateRequest;
import com.blockchainspace.ecommerce.dto.response.ProductResponse;
import com.blockchainspace.ecommerce.dto.response.UserResponse;
import com.blockchainspace.ecommerce.persistence.Product;
import com.blockchainspace.ecommerce.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserService userService;

    public void addProduct(ProductCreateUpdateRequest request) {
        Product existingProduct = productMapper.selectById(request.getCode());
        if (Objects.nonNull(existingProduct)) {
            throw new ProductManagementException(String.format("Product already exists with code [%s] exist", request.getCode()));
        }

        validateCreateUpdatePayload(request);

        Product product = Product.builder().code(request.getCode()).name(request.getName())
                .category(request.getCategory()).publisher(request.getPublisher()).unitOfMeasurement(request.getUom())
                .price(request.getPrice()).quantity(request.getQuantity())
//                .owner() TODO get id from auth/realm shiro
                .build();
        productMapper.insert(product);
    }

    public ProductResponse getProductByCode(String code) {
        return constructResponse(productMapper.selectById(code));
    }

    public List<ProductResponse> getProductList() {
        return productMapper.selectList(null).stream().map(this::constructResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductListBySeller(int sellerId) {
        return productMapper.selectList(new QueryWrapper<Product>().eq("owner", sellerId)).stream()
                .map(this::constructResponse).collect(Collectors.toList());
    }

    public void updateProduct(ProductCreateUpdateRequest request) {
        String code = request.getCode();
        Product existingProduct = productMapper.selectById(code);
        if (Objects.isNull(existingProduct)) {
            throw new ProductManagementException(String.format("Product with code [%s] doesn't exist.", code);
        }

        // TODO find the owner and check if the product belongs to him
        // hint: get from shiro realm/auth
        boolean isProductBelongsToCurrentUser = false; // TODO change this if above condition resolved
        if (!isProductBelongsToCurrentUser) {
            throw new ProductManagementException(String.format("Product with code [%s] doesn't belong to current user", code);
        }

        validateCreateUpdatePayload(request);

        boolean updateItemExists = false;
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        if (!request.getName().equals(existingProduct.getName())) {
            updateItemExists = true;
            updateWrapper.set("name", request.getName());
        }

        if (!request.getCategory().equals(existingProduct.getCategory())) {
            updateItemExists = true;
            updateWrapper.set("category", request.getCategory());
        }

        if (!request.getPublisher().equals(existingProduct.getPublisher())) {
            updateItemExists = true;
            updateWrapper.set("publisher", request.getPublisher());
        }

        if (!request.getUom().equals(existingProduct.getUnitOfMeasurement())) {
            updateItemExists = true;
            updateWrapper.set("unit_of_measurement", request.getUom());
        }

        if (request.getQuantity() != existingProduct.getQuantity()) {
            updateItemExists = true;
            updateWrapper.set("quantity", request.getQuantity());
        }

        if (request.getPrice().compareTo(existingProduct.getPrice()) != 0) {
            updateItemExists = true;
            updateWrapper.set("price", request.getPrice());
        }

        if (updateItemExists) {
            updateWrapper.eq("code", code);
            productMapper.update(new Product(), updateWrapper);
        }
    }

    private void validateCreateUpdatePayload(ProductCreateUpdateRequest request) {
        if (Objects.isNull(request.getName())) {
            throw new ProductManagementException("Product name cannot be null!");
        }

        if (Objects.isNull(request.getPublisher())) {
            throw new ProductManagementException("Product name cannot be null!");
        }
    }

    public void deleteProductByCode(String code) {
        productMapper.deleteById(code);
    }

    private ProductResponse constructResponse(Product product) {
        UserResponse user = userService.getUserById(product.getOwner());
        String seller = user.getFirstName();
        if (Objects.nonNull(user.getLastName())) {
            seller += " " + user.getLastName();
        }

        return ProductResponse.builder().code(product.getCode()).name(product.getName())
                .quantity(product.getQuantity()).price(product.getPrice()).seller(seller).build();
    }

}