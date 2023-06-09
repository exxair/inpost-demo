package com.example.demo.model;

import java.util.List;

public record ExternalEndpointData(int totalPages, List<DataRecord> data) {
}
