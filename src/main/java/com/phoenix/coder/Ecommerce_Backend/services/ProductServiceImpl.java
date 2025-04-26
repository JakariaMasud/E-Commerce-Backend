package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ProductRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public Product addProduct(ProductRequest request) {
        Product product = new Product();
        product.setTitle(request.getName());
        product.setDescription(request.getDescription());
        Product savedProduct=productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }else {
            return null;
        }

    }

    @Override
    public void deleteProduct(Long id) {
            productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(ProductRequest request, Long productId) {
        Product product = getProduct(productId);
        if(!request.getName().equals(product.getTitle())){
            product.setTitle(request.getName());
        }
        if(!request.getDescription().equals(product.getDescription())){
            product.setDescription(request.getDescription());
        }
        return productRepository.save(product);
    }


}
