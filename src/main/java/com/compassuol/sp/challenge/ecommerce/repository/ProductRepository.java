package com.compassuol.sp.challenge.ecommerce.repository;

import com.compassuol.sp.challenge.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndIdNot(String productName, Long id);

    boolean existsByName(String name);
}