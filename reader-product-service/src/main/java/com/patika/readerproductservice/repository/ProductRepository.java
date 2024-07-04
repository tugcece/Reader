package com.patika.readerproductservice.repository;

import com.patika.readerproductservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


     List<Product> getAllByPublisherName(String publisherName) ;

     List<Product> getAllByPublisherId(Long publisherId);


}
