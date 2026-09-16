package org.example.touristguide.repository;

import org.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> touristAttractions = new ArrayList<>(List.of(
            new TouristAttraction("Den lille havfrue", "En meget fin statue","København", List.of("Historie")),
            new TouristAttraction("Noma", "Der er god mad","København",List.of("Mad og drikke")),
            new TouristAttraction("Tivoli","super sjovt","København",List.of("Forlystelser","Mad og drikke")),
            new TouristAttraction("Erhvervsakademi Kobenhavn","Vores skole","København",List.of("Akademi"))
            ));



    public List<TouristAttraction> getAllTouristAttractions() {
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
