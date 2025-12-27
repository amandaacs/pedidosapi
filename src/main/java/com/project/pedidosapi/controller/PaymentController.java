package com.project.pedidosapi.controller;

import com.project.pedidosapi.dto.CreatePaymentRequest;
import com.project.pedidosapi.dto.PaymentResponse;
import com.project.pedidosapi.service.PaymentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResponse register(@RequestBody CreatePaymentRequest request){
        return paymentService.registerPayment(request);
    }

}
