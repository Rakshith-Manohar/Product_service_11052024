package com.example.product_service_11052024.services;

import com.example.product_service_11052024.dtos.FakeStoreDto;
import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.models.Product;

public interface ProductService {
    public Product getsingleProduct(int productId);

    public Product addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price);
}

