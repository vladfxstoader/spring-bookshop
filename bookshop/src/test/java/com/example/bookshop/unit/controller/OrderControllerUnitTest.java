package com.example.bookshop.unit.controller;

import com.example.bookshop.controller.OrderController;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.model.Order;
import com.example.bookshop.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testSave_success() throws Exception {
        OrderDto orderDto = getDummyOrderDtoOne();
        orderDto.setId(null);
        OrderDto savedOrderDto = getDummyOrderDtoOne();
        Mockito.when(orderService.save(Mockito.any(OrderDto.class)))
                .thenReturn(savedOrderDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(savedOrderDto)));
    }

    public Order getDummyOrderOne() {
        Order order = new Order();
        order.setId(15);
        order.setTotalPrice(150F);
        return order;
    }

    public Order getDummyOrderTwo() {
        Order order = new Order();
        order.setId(16);
        order.setTotalPrice(145.45F);
        return order;
    }

    public OrderDto getDummyOrderDtoOne() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(15);
        orderDto.setTotalPrice(150F);
        orderDto.setUserEmail("test@test.com");
        return orderDto;
    }

    public OrderDto getDummyOrderDtoTwo() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(16);
        orderDto.setTotalPrice(145.45F);
        orderDto.setUserEmail("test@test.com");
        return orderDto;
    }

    public List<Order> getDummyOrders() {
        return new ArrayList<>(Arrays.asList(getDummyOrderOne(), getDummyOrderTwo()));
    }

    public List<OrderDto> getDummyOrderDtos() {
        return new ArrayList<>(Arrays.asList(getDummyOrderDtoOne(), getDummyOrderDtoTwo()));
    }

}
