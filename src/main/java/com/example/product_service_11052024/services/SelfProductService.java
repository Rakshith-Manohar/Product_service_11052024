package com.example.product_service_11052024.services;

import com.example.product_service_11052024.exceptions.ProductNotFoundException;
import com.example.product_service_11052024.models.Category;
import com.example.product_service_11052024.models.Product;
import com.example.product_service_11052024.repositories.CategoryRepository;
import com.example.product_service_11052024.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getsingleProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findByIdIs(productId);
        if (product==null){
            throw new ProductNotFoundException(
                    "Product with id "+ productId +" not found!"
            );
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(String title, String description, String imageUrl, String category, double price) {
        Product newProduct=new Product();
        newProduct.setTitle(title);
        newProduct.setDescription(description);
        newProduct.setImageUrl(imageUrl);
        newProduct.setPrice(price);

        Category categoryfromDb = categoryRepository.findByTitle(category);

        if(categoryfromDb==null){
            Category newCategory= new Category();
            newCategory.setTitle(category);
            //categoryRepository.save(newCategory);
            categoryfromDb=newCategory;
        }

        newProduct.setCategory(categoryfromDb);
        Product savedProduct= productRepository.save(newProduct);
        return savedProduct;
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findByIdIs(productId);
        if (product==null){
            throw new ProductNotFoundException(
                    "Product with id "+ productId +" not found!"
            );
        }
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, String title, String description,
                                 String imageUrl, String category, double price) throws ProductNotFoundException {
        Product productinDb=productRepository.findByIdIs(productId);
        if(productinDb==null){
            throw new ProductNotFoundException(
                    "Product with id "+ productId+" not found"
            );

        }
        if(title!=null){
            productinDb.setTitle(title);
        }
        if (description!=null){
            productinDb.setDescription(description);
        }
        if(imageUrl!=null){
            productinDb.setImageUrl(imageUrl);
        }
        if(price!=0){
            productinDb.setPrice(price);
        }
        if(category!=null){
            Category categoryfromDb = categoryRepository.findByTitle(category);
            if(categoryfromDb== null) {
                Category newCategory=new Category();
                newCategory.setTitle(category);
                categoryfromDb=newCategory;
            }
            productinDb.setCategory(categoryfromDb);

        }
        return productRepository.save(productinDb);
    }

    @Override
    public Product replaceProduct(Long productId, String title, String description, String imageUrl, String category, double price) throws ProductNotFoundException {
        return null;
    }
}
