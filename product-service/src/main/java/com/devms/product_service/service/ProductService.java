package com.devms.product_service.service;

import com.devms.product_service.dto.ProductRequest;
import com.devms.product_service.dto.ProductResponse;
import com.devms.product_service.model.Product;
import com.devms.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    RedisService redisService;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        // Save product to Redis cache
        redisService.saveToRedis("name", product.getName());
        log.info("Received {} from redis",redisService.getFromRedis("name"));
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        log.info("Retrieved {} products from the database", products.size());
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}