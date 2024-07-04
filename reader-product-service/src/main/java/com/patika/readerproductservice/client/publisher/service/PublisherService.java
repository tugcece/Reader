package com.patika.readerproductservice.client.publisher.service;

import com.patika.readerproductservice.client.publisher.PublisherClient;
import com.patika.readerproductservice.client.publisher.dto.response.PublisherResponse;
import com.patika.readerproductservice.dto.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PublisherService {
    private final PublisherClient publisherClient;

    public Optional<PublisherResponse> getPublisherByName(String name){
        GenericResponse<Optional<PublisherResponse>> response = publisherClient.getByName(name);
        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getMessage());
        }
        return response.getData();
    }
}
