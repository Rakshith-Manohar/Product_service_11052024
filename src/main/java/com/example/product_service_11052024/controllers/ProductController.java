package com.example.product_service_11052024.controllers;

import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.services.ProductService;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id")int productId){
        return productService.getsingleProduct(productId);

    }

    @PostMapping("/products")
    public ProductResponseDto createNewProduct(@RequestBody ProductResponseDto productResponseDto) {
        return productService.addProduct(
                productResponseDto.getTitle(),
                productResponseDto.getDescription(),
                productResponseDto.getImage(),
                productResponseDto.getCategory(),
                productResponseDto.getPrice()
        );

    }

}
