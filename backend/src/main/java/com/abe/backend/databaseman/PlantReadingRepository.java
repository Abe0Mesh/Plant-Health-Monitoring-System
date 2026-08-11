package com.abe.backend.databaseman;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PlantReadingRepository extends JpaRepository<PlantReading, Long>{
    // currently has basic CRUD operations may add more in the future

    Optional<PlantReading> findFirstByOrderByIdDesc();
}
