package com.foodorder.service;

import com.foodorder.model.Order;
import com.foodorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        // Logic for placing an order, e.g., saving to the database
        return orderRepository.save(order);
    }
}
