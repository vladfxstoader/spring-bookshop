package com.example.bookshop.service;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Order;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private BookMapper bookMapper;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, BookMapper bookMapper, BookRepository bookRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDto> getAll() {
        List<Order> allOrders = orderRepository.findAll();
        return orderMapper.mapListToOrderDto(allOrders);
    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders;
    }

    public Order save(OrderDto orderDto) {
        orderDto.setDate(new Date(System.currentTimeMillis()));
        Order order = orderMapper.map(orderDto, userRepository, bookRepository);
        List<Book> orderedBooks = order.getBooks();
        double totalPrice = orderedBooks.stream().mapToDouble(Book::getPrice).sum();
        order.setTotalPrice((float) totalPrice);
        orderedBooks.forEach(book -> {
            System.out.println(book.getTitle() + " " + book.getQuantity());
            book.setQuantity(book.getQuantity() - 1);
            System.out.println(book.getTitle() + " " + book.getQuantity());
            bookRepository.save(book);
        });
        Order dbOrder = orderRepository.save(order);
        return dbOrder;
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    public List<BookDto> getOrderedBooks(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()) {
            throw new EntityNotFoundException("Order with id " + id + " does not exist.");
        }
        return bookMapper.mapListToBookDto(order.get().getBooks());
    }

    public List<OrderDto> getOrderHistory(String userEmail) {
        List<Order> orders = orderRepository.findAllByUser_Email(userEmail);
        if(orders.size() == 0) {
            throw new EntityNotFoundException("User with email " + userEmail + " has not made any orders.");
        }
        return orderMapper.mapListToOrderDto(orders);
    }

}
