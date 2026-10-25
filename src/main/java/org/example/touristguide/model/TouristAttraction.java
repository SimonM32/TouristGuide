package org.example.touristguide.model;

import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<String> tags;

    private int touristAttraction_id;

    public int getTouristAttraction_id() {
        return touristAttraction_id;
    }

    public void setTouristAttraction_id() {
        this.touristAttraction_id = touristAttraction_id;
    }

    public TouristAttraction() {

    }

    public TouristAttraction(String name, String description, String city, List<String> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;

    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCity(){
        return this.city;
    }
    public List<String> getTags(){
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setTags(List<String> tags){
        this.tags = tags;
    }

}
