package ru.trushkov.productMicroservice.service;

import ru.trushkov.productMicroservice.model.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String productService(CreateProductDto createProductDto) throws InterruptedException, ExecutionException;
}
