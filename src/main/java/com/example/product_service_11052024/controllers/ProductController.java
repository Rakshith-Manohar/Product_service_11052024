package com.example.product_service_11052024.controllers;

import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id")int productId){
        Product product = productService.getsingleProduct(productId);
        return convertToProductResponseDto(product);

    }

    @PostMapping("/products")
    public ProductResponseDto createNewProduct(@RequestBody ProductResponseDto productResponseDto) {
        Product product= productService.addProduct(
                productResponseDto.getTitle(),
                productResponseDto.getDescription(),
                productResponseDto.getImage(),
                productResponseDto.getCategory(),
                productResponseDto.getPrice()
        );
        return convertToProductResponseDto(product);
    }

    private ProductResponseDto convertToProductResponseDto(Product product){
        String categoryTitle= product.getCategory().getTitle();
        ProductResponseDto productResponseDto=modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

}
