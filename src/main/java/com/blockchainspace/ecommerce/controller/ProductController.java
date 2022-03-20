package com.blockchainspace.ecommerce.controller;

import com.blockchainspace.ecommerce.dto.request.ProductCreateUpdateRequest;
import com.blockchainspace.ecommerce.dto.response.ProductResponse;
import com.blockchainspace.ecommerce.service.ProductService;
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
        return productService.getProductByCode(code);
    }

    @GetMapping("/seller/{sellerId}")
    @RequiresPermissions("product:seller-view")
    @RequiresRoles("seller")
    public List<ProductResponse> getProductListBySellerId(@PathVariable int sellerId) {
        return productService.getProductListBySeller(sellerId);
    }

    @PostMapping
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void addProduct(@RequestBody ProductCreateUpdateRequest request) {
        productService.addProduct(request);
    }

    @PutMapping
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void updateProduct(@RequestBody ProductCreateUpdateRequest request) {
        productService.updateProduct(request);
    }

    @DeleteMapping("/{code}")
    @RequiresPermissions("product:manage")
    @RequiresRoles("seller")
    public void deleteProduct(@PathVariable String code) {
        productService.deleteProductByCode(code);
    }

}
