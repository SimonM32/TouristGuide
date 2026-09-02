package org.example.touristguide.repository;

import org.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        this.touristAttractions.add(new TouristAttraction("Den gammel rutsjebane", "En meget sjov forlystelse"));
        this.touristAttractions.add(new TouristAttraction("Det gyldne tarn", "En skræmmende forlystelse"));
        this.touristAttractions.add(new TouristAttraction("Tivoli","super sjovt"));
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
