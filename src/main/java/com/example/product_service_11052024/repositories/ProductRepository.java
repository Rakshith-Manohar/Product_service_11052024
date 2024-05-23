package com.example.product_service_11052024.repositories;

import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.repositories.projections.ProductProjection;
import com.example.product_service_11052024.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    List<Product> findAll();
    Product findByIdIs(Long id);
    List<Product> findAllByCategory_Title(String title);
    List<Product> findAllByCategory_TitleContaining(String title);

    @Query("select p from Product p where p.category.title= :categoryName")
    List<Product> getProductWithCategoryName(String categoryName);

    @Query("select p.title as title from Product  p where p.category.title= :categoryName")
    List<String> someTitleMethod(String categoryName);

    @Query("select p.id as id,p.title as title from Product  p where p.category.title= :categoryName")
    List<ProductWithIdAndTitle> someTitleMethod1(String categoryName);

    @Query("select p.id as id,p.title as title from Product  p where p.category.title= :categoryName")
    List<ProductProjection> someTitleMethod2(String categoryName);

    //NativeSQL
    @Query(value = "select * from Product p where p.id=:id",nativeQuery = true)
    Product someNativeSQL(Long id);
}

//        {
//        "category":"electronics",
//        "title":"nokia flip1",
//        "description":"new flip phone",
//        "price":"106000.00",
//        "imageUrl":"https://cdn0.vox-cdn.com/hermano/verge/product/image/9187/jporter_191001_3703_0001_squ.jpg"
//        }