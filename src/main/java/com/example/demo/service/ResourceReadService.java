package com.example.demo.service;

import com.example.demo.model.DataRecord;
import com.example.demo.model.ExternalEndpointData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ResourceReadService {

    public List<DataRecord> parseRecords() throws IOException {
        File jsonFile = new ClassPathResource("PL.json").getFile();
        ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        return mapper.readValue(jsonFile, ExternalEndpointData.class).data();
    }
}
