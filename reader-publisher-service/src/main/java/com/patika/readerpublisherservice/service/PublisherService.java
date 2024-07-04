package com.patika.readerpublisherservice.service;

import com.patika.readerpublisherservice.converter.PublisherConverter;
import com.patika.readerpublisherservice.dto.request.PublisherSaveRequest;
import com.patika.readerpublisherservice.model.Publisher;
import com.patika.readerpublisherservice.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> getPublisherList() {
        return publisherRepository.findAll();
    }

    public void save(PublisherSaveRequest request) {

        Publisher publisher = PublisherConverter.toPublisher(request);

        publisherRepository.save(publisher);

        log.info("publisher saved. {}", publisher.toString());
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getByName(String publisherName) {
        return getAllPublishers().stream()
                .filter(publisher -> publisher.getName().equals(publisherName))
                .findFirst();
    }
}
