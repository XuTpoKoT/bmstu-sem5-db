package org.example.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(builderMethodName = "internalBuilder")
public class Order {
    private UUID id;
    private UUID customerId;
    private LocalDate date;
    private UUID status;

    public static OrderBuilder builder(UUID id, UUID customerId, LocalDate date, UUID status) {
        return internalBuilder().id(id).customerId(customerId).date(date).status(status);
    }
}
