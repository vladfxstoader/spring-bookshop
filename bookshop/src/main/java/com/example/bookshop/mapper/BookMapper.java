package com.example.bookshop.mapper;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private PublisherMapper publisherMapper;
    private AuthorMapper authorMapper;

    public BookMapper(PublisherMapper publisherMapper, AuthorMapper authorMapper) {
        this.publisherMapper = publisherMapper;
        this.authorMapper = authorMapper;
    }

    public List<Book> mapListToBook(List<BookDto> bookDtos) {
        return bookDtos.stream().map(bookDto -> map(bookDto)).collect(Collectors.toList());
    }

    public List<BookDto> mapListToBookDto(List<Book> books) {
        return books.stream().map(book -> map(book)).collect(Collectors.toList());
    }

    public Book map(BookDto bookDto) {
        if(bookDto == null) {
            return null;
        }
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());
        book.setPublisher(publisherMapper.map(bookDto.getPublisher()));
        book.setAuthors(authorMapper.mapListToAuthor(bookDto.getAuthors()));
        return book;
    }
    public BookDto map(Book book) {
        if(book == null) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setGenre(book.getGenre());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setPublisher(publisherMapper.map(book.getPublisher()));
        bookDto.setAuthors(authorMapper.mapListToAuthorDto(book.getAuthors()));
        return bookDto;
    }
}
