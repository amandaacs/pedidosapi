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

    public List<ProductResponse> listActive(){
        return productRepository.findByActiveTrue()
                .stream()
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
