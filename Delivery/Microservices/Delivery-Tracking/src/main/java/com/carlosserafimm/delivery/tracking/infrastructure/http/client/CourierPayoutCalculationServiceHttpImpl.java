package com.carlosserafimm.delivery.tracking.infrastructure.http.client;

import com.carlosserafimm.delivery.tracking.domain.service.CourierPayoutCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {


    @Autowired
    private CourierAPIClient courierAPIClient;


    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {

        CourierPayoutResultModel courierPayoutResultModel = courierAPIClient.payoutCalculation(new CourierPayoutCalculationInput(distanceInKm));
        return courierPayoutResultModel.getPayoutFee();
    }
}
