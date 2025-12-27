package com.project.pedidosapi.service;

import com.project.pedidosapi.dto.CreatePaymentRequest;
import com.project.pedidosapi.dto.PaymentResponse;
import com.project.pedidosapi.entity.Payment;
import com.project.pedidosapi.repository.OrderRepository;
import com.project.pedidosapi.repository.PaymentRepository;

import java.time.LocalDateTime;

public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public PaymentResponse registerPayment(CreatePaymentRequest request) {

        var order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        var payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(request.method());
        payment.setAmountCents(request.amountCents());
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);

        return new PaymentResponse(
                payment.getId(),
                payment.getMethod(),
                payment.getAmountCents(),
                payment.getPaidAt()
        );

    }

}
