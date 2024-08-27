package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ProductRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
     Product addProduct(ProductRequest request);
     Product updateProduct(Long id,Product product);
     void deleteProduct(Long id);
     Product getProductById(Long id);
     List<Product> getAllProduct();
     Page<Product> getProducts( String category,
                               List<String> colors,List<String> sizes,Integer minPrice,
                               Integer maxPrice,Integer minDiscount,String sort,String stock,
                               Integer pageNumber,Integer pageSize);
}
