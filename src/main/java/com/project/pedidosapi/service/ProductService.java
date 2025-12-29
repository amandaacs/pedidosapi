package com.project.pedidosapi.service;

import com.project.pedidosapi.dto.ProductResponse;
import com.project.pedidosapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public List<ProductResponse> list(String name, String category, Boolean active) {
        return productRepository.findAll().stream()
                .filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> active == null || p.getActive().equals(active))
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getCategory(),
                        p.getPriceCents(),
                        p.getActive()
                ))
                .toList();
    }


}
