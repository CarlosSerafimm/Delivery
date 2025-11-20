package com.carlosSerafimm.courier.management.api.controller;

import com.carlosSerafimm.courier.management.api.model.CourierInput;
import com.carlosSerafimm.courier.management.api.model.CourierPayoutCalculationInput;
import com.carlosSerafimm.courier.management.api.model.CourierPayoutResultModel;
import com.carlosSerafimm.courier.management.domain.model.Courier;
import com.carlosSerafimm.courier.management.domain.repository.CourierRepository;
import com.carlosSerafimm.courier.management.domain.service.CourierPayoutService;
import com.carlosSerafimm.courier.management.domain.service.CourierRegistrationService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/couriers")
public class CourierController {

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private CourierRegistrationService courierRegistrationService;

    @Autowired
    private CourierPayoutService courierPayoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Courier create(@Valid @RequestBody CourierInput input){
        return courierRegistrationService.create(input);
    }

    @PutMapping("/{courierId}")
    public Courier update(@PathVariable UUID courierId,
                          @Valid @RequestBody CourierInput input){
        return courierRegistrationService.update(courierId, input);
    }

    @GetMapping
    public PagedModel<Courier> findAll (@PageableDefault Pageable pageable){
        return new PagedModel<>(courierRepository.findAll(pageable));
    }

    @GetMapping("/{courierId}")
    public Courier findById(@PathVariable UUID courierId){
        return courierRepository.findById(courierId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/payout-calculation")
    @SneakyThrows
    public CourierPayoutResultModel calculate (@RequestBody CourierPayoutCalculationInput input){

        if(Math.random() <0.5){
            throw new RuntimeException();
        }
        int milis = new Random().nextInt(400);
        Thread.sleep(milis);

        BigDecimal payoutFee = courierPayoutService.calculate(input.getDistanceInKm());
        return new CourierPayoutResultModel(payoutFee);

    }
}
