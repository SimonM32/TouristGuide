package org.example.touristguide.service;

import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<String> getCities() {
        return repository.getCities();
    }

    public List<String> getTags() {
        return repository.getTags();
    }

    public List<TouristAttraction> getAllTouristAttractions() {
        return repository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        return repository.getTouristAttractionByName(name);

    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        this.repository.addTouristAttraction(touristAttraction);
    }

    public TouristAttraction updateTouristAttractionByName(String oldName, String newName, String newDescription) {
        return repository.updateTouristAttractionByName(oldName, newName, newDescription);
    }

    public TouristAttraction updateAttraction(String oldName, TouristAttraction updatedAttraction) {
        return repository.updateAttraction(oldName, updatedAttraction);
    }

    public TouristAttraction deleteTouristAttractionByName(String name) {
        return repository.deleteTouristAttractionByName(name);
    }
}




