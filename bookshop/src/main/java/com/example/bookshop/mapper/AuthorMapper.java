package com.example.bookshop.mapper;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    public Author map(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBiography(authorDto.getBiography());
        return author;
    }

    public AuthorDto map(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setBiography(author.getBiography());
        return authorDto;
    }

    public List<AuthorDto> mapListToAuthorDto(List<Author> authors) {
        return authors.stream().map(author -> map(author)).collect(Collectors.toList());
    }

    public List<Author> mapListToAuthor(List<AuthorDto> authorDtos) {
        return authorDtos.stream().map(authorDto -> map(authorDto)).collect(Collectors.toList());
    }
}
