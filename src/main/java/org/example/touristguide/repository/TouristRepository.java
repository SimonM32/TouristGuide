package org.example.touristguide.repository;

import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.model.TouristAttractionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //DENNE METODE VILLE OPRETTE FLERE JAVA OBJEKTER END DEN SKAL HVIS EN ATTRACTION HAR MERE END 1 TAG
    public List<TouristAttraction> getAllTouristAttractions() {
        String sql = """
        SELECT ta.name, ta.description, c.city AS city, tg.tag AS tag
        FROM touristattractions ta
        JOIN cities c ON ta.city_id = c.city_id
        LEFT JOIN touristAttraction_tags tat ON ta.touristAttraction_id = tat.touristAttraction_id
        LEFT JOIN tags tg ON tat.tag_id = tg.tag_id
        """;
        return jdbcTemplate.query(sql, new TouristAttractionRowMapper());
    }

    public int addTouristAttraction(TouristAttraction touristAttraction) {
        String sql = """
                INSERT INTO touristattractions (city_id, name, description)
                VALUES((SELECT city_id FROM cities WHERE city = ?), ?, ?)
                """;
        return jdbcTemplate.update(sql,touristAttraction.getCity(), touristAttraction.getName(), touristAttraction.getDescription());
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        String sql = """
                SELECT name, description, c.city 
                FROM touristAttractions t
                JOIN cities c ON t.city_id = c.city_id
                WHERE t.name = ?
                """;
        TouristAttraction touristAttraction = jdbcTemplate.queryForObject(
                sql, (rs, rowNum) -> new TouristAttraction(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("city"),
                        new ArrayList<>()
                ),
                name
        );

    }

    public List<String> getTagsByAttraction(int attractionID) {
        String sql = """
                SELECT t.tag         
                FROM tags t       
                JOIN touristAttraction_tags tt ON t.tag_id = tt.tag_id
                WHERE tt.touristAttraction_id = ?
                """;

        List hello;

        return jdbcTemplate.query(
                sql,(rs, rowNum) -> rs.getString("tag"), attractionID
        );

    }


    /*
    -------------------------NYT----------------------------------------------------------------
     */

    /*
    private final List<TouristAttraction> touristAttractions = new ArrayList<>(List.of(
            new TouristAttraction("Den lille havfrue", "En meget fin statue","København", List.of("Historie")),
            new TouristAttraction("Noma", "Der er god mad","København",List.of("Mad og drikke")),
            new TouristAttraction("Tivoli","super sjovt","København",List.of("Forlystelser","Mad og drikke")),
            new TouristAttraction("Erhvervsakademi Kobenhavn","Vores skole","København",List.of("Akademi"))
            ));

    public List<String> allCities = List.of(
            "København",
            "Aarhus",
            "Aalborg"
    );

    public List<String> allTags = List.of(
            "Historie",
            "Mad og drikke",
            "Forlystelser",
            "Akademi"
    );


    public List<String> getCities() {
        return allCities;
    }

    public List<String> getTags() {
        return allTags;
    }



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

    public TouristAttraction updateAttraction(String oldName, TouristAttraction updatedAttraction) {
        for (TouristAttraction t : touristAttractions) {
            if (t.getName().equalsIgnoreCase(oldName)) {
                t.setName(updatedAttraction.getName());
                t.setDescription(updatedAttraction.getDescription());
                t.setCity(updatedAttraction.getCity());
                t.setTags(updatedAttraction.getTags());
                return t;
            }
        }
        return null;
    }

    
    public TouristAttraction deleteTouristAttractionByName(String name) {
        TouristAttraction attraction = getTouristAttractionByName(name);
            if(attraction != null) {
                touristAttractions.remove(attraction);
            }
        return attraction;
    }
}
*/