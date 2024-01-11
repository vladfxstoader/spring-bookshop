package com.example.bookshop.unit.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.mapper.PublisherMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.repository.PublisherRepository;
import com.example.bookshop.service.PublisherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublisherServiceUnitTest {
    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private PublisherMapper publisherMapper;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private PublisherService publisherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByCity_success() {
        List<Publisher> publishers = getDummyPublishers();
        List<PublisherDto> publisherDtos = getDummyPublisherDtos();
        Mockito.when(publisherRepository.findAllByCity(Mockito.any()))
                .thenReturn(publishers);
        Mockito.when(publisherMapper.mapListToPublisherDto(publishers))
                .thenReturn(publisherDtos);

        List<PublisherDto> returnedPublishers = publisherService.getByCity("Brasov");

        Assertions.assertNotNull(returnedPublishers, "Returned list should not be null");
        Assertions.assertEquals(publisherDtos.size(), returnedPublishers.size(), "Returned list size should match");

        for (int i = 0; i < returnedPublishers.size(); i++) {
            PublisherDto expectedDto = publisherDtos.get(i);
            PublisherDto returnedDto = returnedPublishers.get(i);

            Assertions.assertEquals(expectedDto.getId(), returnedDto.getId(), "Publisher ID should match");
            Assertions.assertEquals(expectedDto.getName(), returnedDto.getName(), "Publisher name should match");
            Assertions.assertEquals(expectedDto.getCity(), returnedDto.getCity(), "Publisher city should match");
        }
    }

    public Publisher getDummyPublisherOne() {
        Publisher publisher = new Publisher();
        publisher.setId(10);
        publisher.setName("Litera");
        publisher.setCity("Brasov");
        return publisher;
    }

    public Publisher getDummyPublisherTwo() {
        Publisher publisher = new Publisher();
        publisher.setId(11);
        publisher.setName("Egmont");
        publisher.setCity("Bucuresti");
        return publisher;
    }

    public PublisherDto getDummyPublisherDtoOne() {
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(10);
        publisherDto.setName("Litera");
        publisherDto.setCity("Brasov");
        return publisherDto;
    }

    public PublisherDto getDummyPublisherDtoTwo() {
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(11);
        publisherDto.setName("Egmont");
        publisherDto.setCity("Bucuresti");
        return publisherDto;
    }

    public List<Publisher> getDummyPublishers() {
        return new ArrayList<>(Arrays.asList(getDummyPublisherOne(), getDummyPublisherTwo()));
    }

    public List<PublisherDto> getDummyPublisherDtos() {
        return new ArrayList<>(Arrays.asList(getDummyPublisherDtoOne(), getDummyPublisherDtoTwo()));
    }


}
