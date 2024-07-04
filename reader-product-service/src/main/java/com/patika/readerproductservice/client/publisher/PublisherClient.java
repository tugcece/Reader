package com.patika.readerproductservice.client.publisher;

import com.patika.readerproductservice.client.publisher.dto.response.PublisherResponse;
import com.patika.readerproductservice.dto.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "publisher-service", url = "localhost:8095/api/v1/publishers")
public interface PublisherClient {
    @GetMapping("/name/{name}")
        GenericResponse<Optional<PublisherResponse>> getByName(@PathVariable String name);
}
