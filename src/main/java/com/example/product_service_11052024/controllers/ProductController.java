package com.example.product_service_11052024.controllers;

import com.example.product_service_11052024.dtos.ErrorDto;
import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.exceptions.ProductNotFoundException;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.asm.Handle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductDetails(@PathVariable("id")int productId)
    throws ProductNotFoundException {
        Product product = productService.getsingleProduct(productId);
        return convertToProductResponseDto(product);

    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products= productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos= new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(convertToProductResponseDto(product));
        }
        return productResponseDtos;
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

//    //Add Exception Handler
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//        ErrorDto errorDto=new ErrorDto();
//        errorDto.setMessage(productNotFoundException.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }
}
