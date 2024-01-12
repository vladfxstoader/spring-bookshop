package com.example.bookshop.unit.controller;

import com.example.bookshop.controller.PublisherController;
import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.service.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublisherControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private PublisherService publisherService;
    @InjectMocks
    private PublisherController publisherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    public void testGetByCity_success() throws Exception {
        String city = "Bucuresti";
        List<PublisherDto> publishers = getDummyPublisherDtos();
        Mockito.when(publisherService.getByCity(Mockito.any()))
                .thenReturn(publishers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/publisher/city").param("city", city))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(publishers)));
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
