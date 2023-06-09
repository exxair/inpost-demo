package com.example.demo.service;

import com.example.demo.model.DataRecord;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DemoService {

    public final ResourceReadService resourceReadService;

    Map<String, List<DataRecord>> records = new HashMap<>();

    @SneakyThrows
    public List<DataRecord> getRecordsForRegion(String region) {
//        return records.get(region);
        return resourceReadService.parseRecords();
    }
}
