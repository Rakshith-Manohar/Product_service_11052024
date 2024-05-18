package com.example.product_service_11052024.services;

import com.example.product_service_11052024.dtos.FakeStoreDto;
import com.example.product_service_11052024.dtos.ProductResponseDto;
import com.example.product_service_11052024.exceptions.ProductNotFoundException;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final ProductRepository productRepository;
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public Product getsingleProduct(Long productId) throws ProductNotFoundException{

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(
                "http://fakestoreapi.com/products/" + productId,
                FakeStoreDto.class
        );

        if(fakeStoreDto == null){
            throw new ProductNotFoundException(
                    "Product with id "+ productId + " not found. Try some id within 21"
            );
        }

        return fakeStoreDto.toProduct();

    }

    @Override
    public List<Product> getAllProducts(){
        FakeStoreDto[] fakeStoreDtos= restTemplate.getForObject(
                "http://fakestoreapi.com/products/",
                FakeStoreDto[].class
        );
        //convert all fakestore dtos to product objects
        List<Product> products= new ArrayList<>();

        for(FakeStoreDto fakeStoreDto : fakeStoreDtos){
            products.add(fakeStoreDto.toProduct());
        }
        return products;
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

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findByIdIs(productId);
        if(product == null){
            throw new ProductNotFoundException(
                    "Product with id "+ productId + " not found"
            );
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String imageUrl, String category, double price) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, String title, String description, String imageUrl, String category, double price) throws ProductNotFoundException {
        return null;
    }
}
