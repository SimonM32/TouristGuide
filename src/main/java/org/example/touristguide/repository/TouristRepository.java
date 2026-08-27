package org.example.touristguide.repository;

import org.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository(){
        this.touristAttractions.add (new TouristAttraction("Den gammel rutsjebane", "En meget sjov forlystelse"));
        this.touristAttractions.add (new TouristAttraction("Det gyldne tårn", "En skræmmende forlystelse"));

    }





}
