package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from product p " + "where p.category.name=:category and " +
            "p.discountedPrice between :minPrice and :maxPrice and" +
            "p.discountPercent>=:minDiscount " + "order by p.discountedPrice " +
            "case when :sort=price_low_to_high then asc " + "when :sort=price_high_to_low then desc end")
    List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort);


}
