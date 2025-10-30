package com.carlosserafimm.delivery.tracking.domain.service;

import com.carlosserafimm.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
