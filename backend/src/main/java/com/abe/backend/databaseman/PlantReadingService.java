package com.abe.backend.databaseman;

import org.springframework.stereotype.*;

@Service
public class PlantReadingService {

    private final PlantReadingRepository repo;

    public PlantReadingService(PlantReadingRepository repo) {
        this.repo = repo;
    }


    public void saveReading(String recorded_at, double humidity, double Fahrenheit, double Celsius){
        
        PlantReading reading = new PlantReading(recorded_at, humidity, Fahrenheit, Celsius);
        repo.save(reading);
    }
}
