package com.example.bookshop.unit.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Order;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderHistory_success() {
        List<OrderDto> orderDtos = getDummyOrderDtos();
        List<Order> orders = getDummyOrders();
        Mockito.when(orderRepository.findAllByUser_Email(Mockito.any()))
                .thenReturn(orders);
        Mockito.when(orderMapper.mapListToOrderDto(orders))
                .thenReturn(orderDtos);

        List<OrderDto> returnedOrders = orderService.getOrderHistory("test@test.com");

        Assertions.assertNotNull(returnedOrders, "Returned list should not be null");
        Assertions.assertEquals(orderDtos.size(), returnedOrders.size(), "Returned list size should match");

        for (int i = 0; i < returnedOrders.size(); i++) {
            OrderDto expectedDto = orderDtos.get(i);
            OrderDto returnedDto = returnedOrders.get(i);

            Assertions.assertEquals(expectedDto.getId(), returnedDto.getId(), "Order ID should match");
            Assertions.assertEquals(expectedDto.getUserEmail(), returnedDto.getUserEmail(), "Order user email should match");
            Assertions.assertEquals(expectedDto.getTotalPrice(), returnedDto.getTotalPrice(), "Order total price should match");
        }
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
