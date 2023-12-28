package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public Order save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

    @GetMapping("/books/{id}")
    public List<BookDto> getOrderedBooks(@PathVariable Integer id) {
        return orderService.getOrderedBooks(id);
    }

    @GetMapping("/history")
    public List<OrderDto> getOrderHistory(@RequestBody String email) {
        return orderService.getOrderHistory(email);
    }
}
