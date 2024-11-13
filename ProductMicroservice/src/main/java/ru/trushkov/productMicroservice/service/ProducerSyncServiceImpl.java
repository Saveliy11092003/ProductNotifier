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
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ProducerSyncServiceImpl implements ProductService{
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public String productService(CreateProductDto createProductDto) throws InterruptedException, ExecutionException {
        //TODO: save DB
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDto.getTitle(),
                createProductDto.getPrice(), createProductDto.getQuantity());
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
                .send("product-created-events-topic", productId, productCreatedEvent).get();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());

        LOGGER.info("Sent productId - {}", productId);
        return productId;
    }
}
