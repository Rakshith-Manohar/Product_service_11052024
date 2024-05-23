package com.example.product_service_11052024.repositories.projections;

import com.example.product_service_11052024.models.Category;

import java.math.BigDecimal;

public interface ProductProjection {
    Long getId();
    String getTitle();
    BigDecimal getPrice();
    String getDescription();
    Category getCategory();
    String getImageUrl();
}
