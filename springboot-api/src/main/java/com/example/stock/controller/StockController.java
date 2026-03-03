package com.example.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/predict/{symbol}")
    public String predict(@PathVariable String symbol) {

        String fastApiUrl = "http://localhost:8000/predict?symbol=" + symbol;

        String response = restTemplate.getForObject(fastApiUrl, String.class);

        return response;
    }
}