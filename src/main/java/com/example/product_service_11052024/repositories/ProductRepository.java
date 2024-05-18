package com.example.product_service_11052024.repositories;

import com.example.product_service_11052024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long id);
}

//        {
//        "category":"electronics",
//        "title":"nokia flip1",
//        "description":"new flip phone",
//        "price":"106000.00",
//        "imageUrl":"https://cdn0.vox-cdn.com/hermano/verge/product/image/9187/jporter_191001_3703_0001_squ.jpg"
//        }