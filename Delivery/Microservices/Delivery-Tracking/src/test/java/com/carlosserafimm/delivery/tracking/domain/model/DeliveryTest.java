package com.carlosserafimm.delivery.tracking.domain.model;

import com.carlosserafimm.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced(){
        Delivery delivery = Delivery.draft();
        
        delivery.editPreparationDetails(createValidPreparationDetails());
        
        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlaced(){
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, () ->{
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }

    private Delivery.PreparationDetails createValidPreparationDetails() {

        ContactPoint sender = ContactPoint.builder()
                .zipCode("12345")
                .street("Rua São Paulo")
                .number("1")
                .complement("Sala 1")
                .name("João")
                .phone("(11) 99999-9999")
                .build();

                ContactPoint recipient = ContactPoint.builder()
                .zipCode("1111")
                .street("Rua Rio de Janeiro")
                .number("2")
                .complement("")
                .name("Luis")
                .phone("(22) 88888-8888")
                .build();
        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }


}