package com.project.pedidosapi.service;

import com.project.pedidosapi.dto.CreateOrderRequest;
import com.project.pedidosapi.dto.OrderItemResponse;
import com.project.pedidosapi.dto.OrderResponse;
import com.project.pedidosapi.dto.PaymentResponse;
import com.project.pedidosapi.entity.Order;
import com.project.pedidosapi.entity.OrderItem;
import com.project.pedidosapi.entity.Payment;
import com.project.pedidosapi.exception.BusinessException;
import com.project.pedidosapi.repository.CustomerRepository;
import com.project.pedidosapi.repository.OrderRepository;
import com.project.pedidosapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderResponse> listAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public OrderResponse getOrderById(Long id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pedido não encontrado"));

        return toResponse(order);
    }

    private int calculateTotal(Order order) {
        return order.getItems().stream()
                .mapToInt(i -> i.getQuantity() * i.getUnitPriceCents())
                .sum();
    }


    public OrderResponse createOrder(CreateOrderRequest request) {
        var customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        var order = new Order();
        order.setCustomer(customer);
        order.setStatus("NOVO");
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items =  request.items().stream()
                .map(i -> {
                    var product = productRepository.findById(i.productId())
                            .orElseThrow(() -> new BusinessException("Produto não encontrado"));

                    if (!product.getActive()) {
                        throw new BusinessException("Produto inativo: " + product.getName());
                    }

                    var item = new OrderItem();
                    item.setOrder(order);
                    item.setProduct(product);
                    item.setQuantity(i.quantity());
                    item.setUnitPriceCents(product.getPriceCents());
                    return item;
                }).toList();

        order.setItems(items);
        orderRepository.save(order);
        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        var items = order.getItems() != null ? order.getItems() : List.<OrderItem>of();
        var payments = order.getPayments() != null ? order.getPayments() : List.<Payment>of();

        int total = calculateTotal(order);
        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getName(),
                order.getStatus(),
                order.getCreatedAt(),
                total,
                items.stream().map(i -> new OrderItemResponse(
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getUnitPriceCents()
                )).toList(),
                payments.stream().map(p -> new PaymentResponse(
                        p.getId(),
                        p.getMethod(),
                        p.getAmountCents(),
                        p.getPaidAt()
                )).toList()
        );
    }

}
