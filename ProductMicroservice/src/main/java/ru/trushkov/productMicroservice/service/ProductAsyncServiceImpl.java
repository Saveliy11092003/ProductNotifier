package ru.trushkov.productMicroservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.trushkov.core.model.ProductCreatedEvent;
import ru.trushkov.productMicroservice.model.dto.CreateProductDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProductAsyncServiceImpl implements ProductService{

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public String productService(CreateProductDto createProductDto) {
        //TODO: save DB
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDto.getTitle(),
                createProductDto.getPrice(), createProductDto.getQuantity());
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("Error - {}", exception.getMessage());
            } else {
                LOGGER.info("Message sent successfully - {}", result.getRecordMetadata());
            }
        });

        LOGGER.info("Sent productId - {}", productId);
        return productId;
    }
}
