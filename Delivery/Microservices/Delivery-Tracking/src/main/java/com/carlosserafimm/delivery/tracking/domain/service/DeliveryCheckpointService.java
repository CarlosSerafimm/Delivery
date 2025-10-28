package com.carlosserafimm.delivery.tracking.domain.service;

import com.carlosserafimm.delivery.tracking.domain.exception.DomainException;
import com.carlosserafimm.delivery.tracking.domain.model.Delivery;
import com.carlosserafimm.delivery.tracking.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DeliveryCheckpointService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void place (UUID deliveryID){
        Delivery delivery = deliveryRepository.findById(deliveryID).orElseThrow(() -> new DomainException());
        delivery.place();
        deliveryRepository.saveAndFlush(delivery);
    }
    public void pickup (UUID deliveryID, UUID courierId){
        Delivery delivery = deliveryRepository.findById(deliveryID).orElseThrow(() -> new DomainException());
        delivery.pickUp(courierId);
        deliveryRepository.saveAndFlush(delivery);
    }
    public void complete (UUID deliveryID){
        Delivery delivery = deliveryRepository.findById(deliveryID).orElseThrow(() -> new DomainException());
        delivery.markAsDelivered();
        deliveryRepository.saveAndFlush(delivery);
    }
}
