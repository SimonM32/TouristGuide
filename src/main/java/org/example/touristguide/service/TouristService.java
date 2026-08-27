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

    public void addTouristAttraction(String name, String description) {
        this.repository.addTouristAttraction(new TouristAttraction(name, description));
    }

    public void updateTouristAttractionByName(String name, TouristAttraction touristAttraction) {
        repository.updateTouristAttractionByName(name);
    }

    public void deleteTouristAttractionByName(String name) {
        repository.deleteTouristAttractionByName(name);
    }


}
