package com.abe.backend.databaseman;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity // shape of a table in the db
public class PlantReading {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String recorded_at;
    private double Humidity;
    private double Fahrenheit;
    private double Celsius;

    protected PlantReading(){}

    public PlantReading(String recorded_at, double humidity, double tempF, double tempC){
        this.recorded_at = recorded_at;
        this.Humidity = humidity;
        this.Fahrenheit = tempF;
        this.Celsius = tempC;
    }
}
