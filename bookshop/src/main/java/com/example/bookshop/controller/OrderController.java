package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order", description = "Order management APIs")
@RequestMapping("order")
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Get all orders")
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @Operation(summary = "Place a new order")
    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @Operation(summary = "Delete an order by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.delete(id);
    }

    @Operation(summary = "Get list of ordered books using order id")
    @GetMapping("/books/{id}")
    public List<BookDto> getOrderedBooks(@PathVariable Integer id) {
        return orderService.getOrderedBooks(id);
    }

    @Operation(summary = "Get order history for an user")
    @GetMapping("/history")
    public List<OrderDto> getOrderHistory(@RequestBody String email) {
        return orderService.getOrderHistory(email);
    }
}
