package com.phoenix.coder.Ecommerce_Backend.repositories;

import com.phoenix.coder.Ecommerce_Backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    @Query("select c from category where c.name=:name and c.parentCategory.name=:parentName")
    Category findByNameAndParentName(@Param("name") String name, @Param("parentName") String parentName);

}
