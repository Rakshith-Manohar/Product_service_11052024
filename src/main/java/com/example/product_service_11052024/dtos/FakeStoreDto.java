package com.example.product_service_11052024.dtos;

import com.example.product_service_11052024.models.Category;
import com.example.product_service_11052024.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category category1=new Category();
        category1.setTitle(category);
        product.setCategory(category1);
        return product;
    }
}
