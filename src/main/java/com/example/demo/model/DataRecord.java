package com.example.demo.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record DataRecord(UUID id,
                         String name,
                         String type,
                         int numbersOfParcel,
                         String description,
                         OffsetDateTime startOfContractDate,
                         List<Income> income) {
}
