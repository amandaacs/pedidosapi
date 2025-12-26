package com.project.pedidosapi.repository;

import com.project.pedidosapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerIdOrderByCreatedAt(Long customerId);
    List<Order> findByStatusOrderByCreatedAt(String status);
}
