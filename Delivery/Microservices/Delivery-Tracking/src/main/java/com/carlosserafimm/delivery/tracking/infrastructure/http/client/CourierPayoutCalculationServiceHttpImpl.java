package com.carlosserafimm.delivery.tracking.infrastructure.http.client;

import com.carlosserafimm.delivery.tracking.domain.service.CourierPayoutCalculationService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;

@Service
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {


    @Autowired
    private CourierAPIClient courierAPIClient;


    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {

        try {
            CourierPayoutResultModel courierPayoutResultModel = courierAPIClient.payoutCalculation(new CourierPayoutCalculationInput(distanceInKm));
            return courierPayoutResultModel.getPayoutFee();

        }catch (ResourceAccessException e){
            throw new GatewayTimeoutException(e.getMessage());
        }catch (HttpServerErrorException e){
            throw new BadGatewayException(e.getMessage());
        }catch (IllegalArgumentException e){
            throw new BadGatewayException(e.getMessage());
        }catch (CallNotPermittedException e){
            throw new BadGatewayException(e.getMessage());
        }
    }
}
