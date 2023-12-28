package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Order;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private BookMapper bookMapper;
    private UserMapper userMapper;

    public OrderMapper(BookMapper bookMapper, UserMapper userMapper) {
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
    }

    public List<Order> mapListToOrder(List<OrderDto> orderDtos, UserRepository userRepository, BookRepository bookRepository) {
        return orderDtos.stream().map(orderDto -> map(orderDto, userRepository, bookRepository)).collect(Collectors.toList());
    }

    public List<OrderDto> mapListToOrderDto(List<Order> orders) {
        return orders.stream().map(order -> map(order)).collect(Collectors.toList());
    }

    public Order map(OrderDto orderDto, UserRepository userRepository, BookRepository bookRepository) {
        if(orderDto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDate(orderDto.getDate());
        Optional<User> user = userRepository.findByEmail(orderDto.getUserEmail());
        if(!user.isPresent()) {
            throw new EntityNotFoundException("User with email " + orderDto.getUserEmail() + " does not exist in the database.");
        }
        order.setUser(user.get());
        List<Book> books = new ArrayList<>();
        orderDto.getBookTitles().forEach(bookTitle -> {
            Optional<Book> currentBook = bookRepository.findByTitle(bookTitle);
            if(!currentBook.isPresent()) {
                throw new EntityNotFoundException("Book titled " + bookTitle + " does not exist in the database.");
            }
            books.add(currentBook.get());
        });
        order.setBooks(books);
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }

    public OrderDto map(Order order) {
        if(order == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUserEmail(order.getUser().getEmail());
        List<String> bookTitles = new ArrayList<>();
        order.getBooks().forEach(book -> bookTitles.add(book.getTitle()));
        orderDto.setBookTitles(bookTitles);
        return orderDto;
    }
}
