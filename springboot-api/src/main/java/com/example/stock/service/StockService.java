package com.stock.service;

import com.stock.model.Prediction;
import com.stock.repository.PredictionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class StockService {

    private final PredictionRepository repo;

    public StockService(PredictionRepository repo) {
        this.repo = repo;
    }

    public Prediction predict(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8000/predict?symbol=" + symbol;

        Map response = restTemplate.getForObject(url, Map.class);
        Double price = Double.valueOf(response.get("predicted_price").toString());

        Prediction prediction = new Prediction(null, symbol, price);
        return repo.save(prediction);
    }
}