package org.example.touristguide.repository;

import org.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        this.touristAttractions.add(new TouristAttraction("Den lille havfrue", "En meget fin statue"));
        this.touristAttractions.add(new TouristAttraction("Noma", "Der er god mad"));
        this.touristAttractions.add(new TouristAttraction("Tivoli","super sjovt"));
        this.touristAttractions.add(new TouristAttraction("Erhvervsakademi Kobenhavn","Vores skole"));

    }


    public ArrayList<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }


    public TouristAttraction getTouristAttractionByName(String name) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }

    public TouristAttraction updateTouristAttractionByName(String oldName, String newName, String newDescription) {

        TouristAttraction touristAttraction =
                getTouristAttractionByName(oldName);

        if (touristAttraction == null) {
            return null;
        }

        touristAttraction.setName(newName);
        touristAttraction.setDescription(newDescription);
        return touristAttraction;
    }
    
    public TouristAttraction deleteTouristAttractionByName(String name) {
        TouristAttraction attraction = getTouristAttractionByName(name);
            if(attraction != null) {
                touristAttractions.remove(attraction);
            }
        return attraction;
    }
}
