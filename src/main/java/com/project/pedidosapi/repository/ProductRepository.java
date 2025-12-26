package com.project.pedidosapi.repository;

import com.project.pedidosapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();
    List<Product> findByActiveAndCategory(String category);
    List<Product> findByActiveAndNameContaining(String name);

}
