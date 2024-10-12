package com.foodorder.controller;

import com.foodorder.model.Order;
import com.foodorder.service.OrderService;
import com.foodorder.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);

        // Call payment service
        try {
            String paymentId = paymentService.processPayment(order);
            return ResponseEntity.ok("Order placed successfully. Payment ID: " + paymentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Failed");
        }
    }
}

