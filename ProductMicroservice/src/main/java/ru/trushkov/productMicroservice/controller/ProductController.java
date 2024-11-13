package ru.trushkov.productMicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trushkov.productMicroservice.model.ErrorMessage;
import ru.trushkov.productMicroservice.model.dto.CreateProductDto;
import ru.trushkov.productMicroservice.service.ProductService;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("producerSyncServiceImpl")
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto) {
        String productId = null;
        try {
            productId = productService.productService(createProductDto);
        } catch (Exception e) {
            LOGGER.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
