package org.example.touristguide.service;

import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TouristService {
    private TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAllTouristAttractions() {
        return repository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        TouristAttraction touristAttraction = repository.getTouristAttractionByName(name);
        return touristAttraction;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        this.repository.addTouristAttraction(touristAttraction);
    }


    public TouristAttraction deleteTouristAttractionByName(String name) {
        return repository.deleteTouristAttractionByName(name);
    }
}




