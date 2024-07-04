package com.patika.readerpublisherservice.controller;

import com.patika.readerpublisherservice.dto.request.PublisherSaveRequest;
import com.patika.readerpublisherservice.dto.response.GenericResponse;
import com.patika.readerpublisherservice.model.Publisher;
import com.patika.readerpublisherservice.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/publishers")

public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PublisherSaveRequest request) {
        publisherService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Publisher> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/name/{name}")
    public GenericResponse<Optional<Publisher>> getByName(@PathVariable String name) {
        Optional<Publisher> publisher = publisherService.getByName(name);
        return GenericResponse.success(publisher);
    }
}
