package com.carlosserafimm.delivery.tracking.infrastructure.event;

import com.carlosserafimm.delivery.tracking.domain.event.DeliveryFulfilledEvent;
import com.carlosserafimm.delivery.tracking.domain.event.DeliveryPickUpEvent;
import com.carlosserafimm.delivery.tracking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.carlosserafimm.delivery.tracking.infrastructure.kafka.KafkaTopicConfig.deliveryEventsTopicName;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {

    private final IntegrationEventPublisher publisher;

    @EventListener
    public void handle(DeliveryPlacedEvent event) {
        log.info(event.toString());
        publisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
    }

    @EventListener
    public void handle(DeliveryFulfilledEvent event) {
        log.info(event.toString());
        publisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);

    }

    @EventListener
    public void handle(DeliveryPickUpEvent event) {
        log.info(event.toString());
        publisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);

    }
}
