package com.carlosSerafimm.courier.management.domain.service;

import com.carlosSerafimm.courier.management.api.model.CourierInput;
import com.carlosSerafimm.courier.management.domain.model.Courier;
import com.carlosSerafimm.courier.management.domain.repository.CourierRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourierRegistrationService {

    @Autowired
    private CourierRepository courierRepository;
    
    public Courier create(@Valid CourierInput input) {
        Courier courier = Courier.brandNew(input.getName(), input.getPhone());
        return courierRepository.saveAndFlush(courier);
    }

    public Courier update(UUID courierId, @Valid CourierInput input) {
        Courier courier = courierRepository.findById(courierId).orElseThrow();
        courier.setPhone(input.getPhone());
        courier.setName(input.getName());
        return courierRepository.saveAndFlush(courier);
    }
}
