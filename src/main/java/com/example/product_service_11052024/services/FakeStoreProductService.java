package com.example.product_service_11052024.services;

import com.example.product_service_11052024.dtos.FakeStoreDto;
import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getsingleProduct(int productId) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(
                "http://fakestoreapi.com/products/" + productId,
                FakeStoreDto.class
        );

        return fakeStoreDto.toProduct();

    }

    @Override
    public Product addProduct(
            String title,
            String description,
            String imageUrl,
            String category,
            double price) {

        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(imageUrl);
        fakeStoreDto.setCategory(category);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response = restTemplate.postForObject(
                "http://fakestoreapi.com/products/",
                fakeStoreDto,
                FakeStoreDto.class
        );
        return response.toProduct();
    }
}
