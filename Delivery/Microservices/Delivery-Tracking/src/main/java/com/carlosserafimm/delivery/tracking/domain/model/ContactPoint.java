package com.carlosserafimm.delivery.tracking.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ContactPoint {

    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String name;
    private String phone;
}
