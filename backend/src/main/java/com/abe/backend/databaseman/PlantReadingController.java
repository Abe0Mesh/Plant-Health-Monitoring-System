package com.abe.backend.databaseman;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/readings")
public class PlantReadingController {

    private final PlantReadingService prs;

    public PlantReadingController(PlantReadingService prs) {
        this.prs = prs;
    }

    @GetMapping
    public List<PlantReading> getReading() {
        return prs.getAllReadings();
    }

    @GetMapping("/latest")
    public PlantReading getLatest(){
        return prs.getLatestReading();
    }

    

}
