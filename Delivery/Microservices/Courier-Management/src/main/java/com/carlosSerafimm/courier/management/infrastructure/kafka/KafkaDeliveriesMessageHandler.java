package com.carlosSerafimm.courier.management.infrastructure.kafka;

import com.carlosSerafimm.courier.management.domain.service.CourierDeliveryService;
import com.carlosSerafimm.courier.management.infrastructure.DeliveryFulfilledIntegrationEvent;
import com.carlosSerafimm.courier.management.infrastructure.DeliveryPlacedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"deliveries.v1.events"}, groupId = "courier-management")
@Slf4j
@RequiredArgsConstructor
public class KafkaDeliveriesMessageHandler {

    private final CourierDeliveryService courierDeliveryService;

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object object){
        log.info("Default handler: {}", object);
    }

    @KafkaHandler
    public void handlerPlaced(@Payload DeliveryPlacedIntegrationEvent event){
        log.info("Placed handler: {}", event);
        courierDeliveryService.assign(event.getDeliveryId());

    }
    @KafkaHandler
    public void handlerFulfilled(@Payload DeliveryFulfilledIntegrationEvent event){
        log.info("Fulfilled handler: {}", event);
        courierDeliveryService.fulfill(event.getDeliveryId());

    }
}
