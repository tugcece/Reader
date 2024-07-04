package com.patika.readerpublisherservice.converter;

import com.patika.readerpublisherservice.dto.request.PublisherSaveRequest;
import com.patika.readerpublisherservice.model.Publisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherConverter {

    public static Publisher toPublisher(PublisherSaveRequest request) {
        return Publisher.builder()
                .name(request.getName())
                .creatDate(request.getCreateDate())
                .build();
    }
}
