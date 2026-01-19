package com.devms.product_service.controller;

import com.devms.product_service.dto.ProductRequest;
import com.devms.product_service.dto.ProductResponse;
import com.devms.product_service.model.Product;
import com.devms.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest)
    {
        productService.createProduct(productRequest);
        log.info("Product created: {}", productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct()
    {
        log.info("Fetched all products");
        return productService.getAllProducts();

    }
}
