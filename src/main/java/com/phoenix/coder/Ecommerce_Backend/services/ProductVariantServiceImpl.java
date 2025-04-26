package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.AttributeRequest;
import com.phoenix.coder.Ecommerce_Backend.dtos.VariantRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.models.ProductAttribute;
import com.phoenix.coder.Ecommerce_Backend.models.ProductVariant;
import com.phoenix.coder.Ecommerce_Backend.repositories.ProductVariantRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Data
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductService productService;
    private final ProductVariantRepository productVariantRepository;
    @Override
    public ProductVariant addProductVariant(VariantRequest request, Long productId) {
        Product product=productService.getProduct(productId);
        ProductVariant productVariant = new ProductVariant();
        productVariant.setProduct(product);
        productVariant.setPrice(request.getPrice());
        productVariant.setStockQuantity(request.getStockQuantity());
        productVariant.setSku(request.getSku());
        List<ProductAttribute> attributeList= attributeRequestToAttribute(request.getAttributes());
        productVariant.setAttributes(attributeList);
        productVariant.setCreatedAt(LocalDateTime.now());
        productVariant.setUpdatedAt(LocalDateTime.now());

        return productVariantRepository.save(productVariant);
    }

    @Override
    public ProductVariant updateProductVariant(VariantRequest request, Long variantId){
        ProductVariant productVariant=getProductVariant(variantId);
        if(!productVariant.getStockQuantity().equals(request.getStockQuantity())){
            productVariant.setStockQuantity(request.getStockQuantity());
        }
        if(!productVariant.getSku().equals(request.getSku())){
            productVariant.setSku(request.getSku());
        }
        List<ProductAttribute> attributeList= attributeRequestToAttribute(request.getAttributes());
        if(!productVariant.getAttributes().equals(attributeList)){
            productVariant.setAttributes(attributeList);
        }
        return null;
    }

    @Override
    public ProductVariant getProductVariant(Long variant) {
        Optional<ProductVariant> optionalProductVariant=productVariantRepository.findById(variant);
        if(optionalProductVariant.isPresent()){
            return optionalProductVariant.get();
        }
        return null;
    }

    @Override
    public void deleteProductVariant(Long variant) {
        productVariantRepository.deleteById(variant);
    }

    public List<ProductAttribute> attributeRequestToAttribute(List<AttributeRequest> attributeRequests){
        List<ProductAttribute> productAttributeList=new ArrayList<>();
        for(AttributeRequest attr : attributeRequests){
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setName(attr.getName());
            productAttribute.setValue(attr.getValue());
            productAttributeList.add(productAttribute);
        }
        return productAttributeList;
    }
}
