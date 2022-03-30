package com.blockchainspace.ecommerce.controller;

import com.blockchainspace.ecommerce.dto.request.ProductCreateUpdateRequest;
import com.blockchainspace.ecommerce.dto.response.ProductResponse;
import com.blockchainspace.ecommerce.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @RequiresPermissions("product:view")
    @RequiresRoles("buyer")
    public List<ProductResponse> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/{code}")
    @RequiresPermissions(logical = Logical.OR, value = {"product:view", "product:seller-view"})
    public ProductResponse getProductById(@PathVariable String code) {
//        return productServic.getProductByCode(code);
        return null;
    }

    @GetMapping("/seller/{sellerId}")
    @RequiresPermissions("product:seller-view")
    @RequiresRoles("seller")
    public List<ProductResponse> getProductListBySellerId(@PathVariable int sellerId) throws JsonProcessingException {
        return productService.getProductListBySeller(sellerId);
    }

    @PostMapping("/seller/{sellerId}")
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void addProduct(@PathVariable int sellerId, @RequestBody ProductCreateUpdateRequest request) {
        productService.addProduct(request, sellerId);
    }

    @PutMapping("/seller/{sellerId}")
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void updateProduct(@PathVariable int sellerId, @RequestBody ProductCreateUpdateRequest request) {
        productService.updateProduct(request, sellerId);
    }

    @DeleteMapping("/{code}")
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void deleteProduct(@PathVariable String code) {
        productService.deleteProductByCode(code);
    }

}
