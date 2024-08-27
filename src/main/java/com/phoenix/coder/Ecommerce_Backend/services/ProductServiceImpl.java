package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.dtos.ProductRequest;
import com.phoenix.coder.Ecommerce_Backend.models.Category;
import com.phoenix.coder.Ecommerce_Backend.models.Product;
import com.phoenix.coder.Ecommerce_Backend.repositories.CategoryRepository;
import com.phoenix.coder.Ecommerce_Backend.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    UserModelService userModelService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserModelService userModelService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userModelService = userModelService;
    }

    @Override
    public Product addProduct(ProductRequest request) {
        Category topLevelCategory = categoryRepository.findByName(request.getTopLevelCategory());
        if (topLevelCategory == null) {
            Category category = new Category();
            category.setLevel(1);
            category.setName(request.getTopLevelCategory());
            topLevelCategory = categoryRepository.save(category);
        }
        Category secondLevelCategory = categoryRepository.findByNameAndParentName(request.getSecondLevelCategory(), topLevelCategory.getName());
        if (secondLevelCategory == null) {
            Category category = new Category();
            category.setLevel(2);
            category.setName(request.getSecondLevelCategory());
            secondLevelCategory = categoryRepository.save(category);
        }
        Category thirdLevelCategory = categoryRepository.findByNameAndParentName(request.getThirdLevelCategory(), secondLevelCategory.getName());
        if (thirdLevelCategory == null) {
            Category category = new Category();
            category.setLevel(3);
            category.setName(request.getThirdLevelCategory());
            thirdLevelCategory = categoryRepository.save(category);
        }
        Product product = new Product();
        product.setTitle(request.getTitle());
        //set other property here
        product.setCategory(thirdLevelCategory);
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product savedProduct= optionalProduct.get();
            //set updated property of product here
            savedProduct.setTitle(product.getTitle());
            savedProduct.setDescription(product.getDescription());
            savedProduct.setDiscountedPrice(product.getDiscountedPrice());
            return productRepository.save(savedProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    @Override
    public Page<Product> getProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        List<Product> allProducts=productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        List<Product> products = List.of();
        if(!colors.isEmpty()){
            products= allProducts.stream().filter(p->colors.stream().anyMatch(color->p.getColor().equalsIgnoreCase(color))).collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equalsIgnoreCase("in_stock")){
                products= products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }else if(stock.equalsIgnoreCase("out_of_stock")){
                products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        Integer startIndex= (int) pageable.getOffset();
        Integer endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent= products.subList(startIndex,endIndex);
        Page<Product> filteredProducts= new PageImpl<>(pageContent,pageable,products.size());

        return filteredProducts;
    }
}
