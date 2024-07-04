package com.patika.readerorderservice.client.product;

import com.patika.readerorderservice.client.product.dto.request.ProductListSearchRequest;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.dto.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "product-service", url = "localhost:8093/api/v1/products")
public interface ProductClient {

    @GetMapping("/getByIds")
     GenericResponse<List<ProductResponse>> getByIdList(@RequestBody List<Long> productIds);
}
