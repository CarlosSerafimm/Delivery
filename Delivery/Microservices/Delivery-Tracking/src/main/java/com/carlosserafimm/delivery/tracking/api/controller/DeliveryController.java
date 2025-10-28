package com.carlosserafimm.delivery.tracking.api.controller;

import com.carlosserafimm.delivery.tracking.api.model.DeliveryInput;
import com.carlosserafimm.delivery.tracking.domain.model.Delivery;
import com.carlosserafimm.delivery.tracking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryPreparationService deliveryPreparationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Delivery draft(@RequestBody @Valid DeliveryInput input){
        return deliveryPreparationService.draft(input);
    }

    @PutMapping("{deliveryId}")
    public Delivery edit(@PathVariable UUID deliveryId,
            @RequestBody @Valid DeliveryInput input){
        return deliveryPreparationService.edit(deliveryId, input);
    }

}
