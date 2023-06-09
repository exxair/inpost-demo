package com.example.demo.controller;

import com.example.demo.model.Income;
import com.example.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DemoController {

    public final DemoService demoService;

    @GetMapping("merchants/{regionID}")
    public Double getAverage(@PathVariable @Validated String regionID,
                             @RequestParam String currency,
                             @RequestParam List<String> types,
                             @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate dateSince,
                             @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate dateUntil) {
        var records = demoService.getRecordsForRegion(regionID);
        return records.stream()
                .filter(r -> r.startOfContractDate().isBefore(OffsetDateTime.of(dateUntil, LocalTime.NOON, ZoneOffset.UTC))
                        && r.startOfContractDate().isAfter(OffsetDateTime.of(dateSince, LocalTime.NOON, ZoneOffset.UTC)))
                .filter(r -> types.contains(r.type()))
                .flatMap(r -> r.income().stream())
                .filter(i -> i.currency().equals(currency))
                .mapToDouble(Income::value)
                .average()
                .orElseThrow();
    }
}
