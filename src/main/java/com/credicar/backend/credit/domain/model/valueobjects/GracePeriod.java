package com.credicar.backend.credit.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record GracePeriod(String type, Integer months) {
}
