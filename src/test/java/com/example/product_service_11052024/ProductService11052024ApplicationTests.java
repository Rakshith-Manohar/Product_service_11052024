package com.example.product_service_11052024;

import com.example.product_service_11052024.models.Category;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.repositories.CategoryRepository;
import com.example.product_service_11052024.repositories.ProductRepository;
import com.example.product_service_11052024.repositories.projections.ProductProjection;
import com.example.product_service_11052024.repositories.projections.ProductWithIdAndTitle;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductService11052024ApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testJpaDeclaredjoin(){
        List<Product> products = productRepository.findAllByCategory_Title("electronics");
        for(Product product : products){
            System.out.println(product.getTitle());
        }

    }

    @Test
    void testHql(){
        List<Product> products = productRepository.getProductWithCategoryName("electronics");
        for(Product product : products){
            System.out.println(product.getTitle());
            System.out.println(product.getCategory().getTitle());
            System.out.println(product.getPrice());
        }
    }

    @Test
    void testSpecificField(){
        List<String> productTitle = productRepository.someTitleMethod("electronics");
        for(String title : productTitle){
            System.out.println(title);
        }
    }

    @Test
    void testProjections(){
        List<ProductWithIdAndTitle> products= productRepository.someTitleMethod1("electronics");
        for(ProductWithIdAndTitle product : products){
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }

        List<ProductProjection> projections = productRepository.someTitleMethod2("electronics");
        for(ProductProjection product : projections){
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }
    }

    @Test
    void testNativeSQL(){
        Product product=   productRepository.someNativeSQL(5L);
        System.out.println(product.getTitle());
        System.out.println(product.getId());
    }

    @Test
    @Transactional
    void testFetchType(){
        Optional<Category> category = categoryRepository.findById(2L);
        if(category.isPresent()){
            System.out.println(category.get().getTitle());
            List<Product> products = category.get().getProducts();
            for(Product product : products){
                System.out.println(product.getTitle());
            }
        }
    }

    @Test
    @Transactional
    void testFetchMode(){
        List<Category> categories = categoryRepository.findByTitleEndingWith("electronics");
        for(Category category : categories){
            System.out.println(category.getTitle());
            List<Product> products = category.getProducts();
            for(Product product : products){
                System.out.println(product.getTitle());
            }
        }
    }
}
