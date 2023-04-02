package com.amcart.products.repositories;

import com.amcart.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.categoryId IN (SELECT pc.id FROM ProductCategory pc WHERE pc.categoryName LIKE CONCAT(:category,'%'))")
    Page<Product> findByCategory(@Param("category") String category, Pageable pageable);

    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
