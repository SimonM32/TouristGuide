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
/*
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

 */

    public List<TouristAttraction> getAllTouristAttractions() {

        String sql = """
                SELECT t.touristAttraction_id, t.name, t.description, c.city AS city
                FROM touristAttractions t
                JOIN cities c ON t.city_id = c.city_id              
                """;

        List<TouristAttraction> attractions = jdbcTemplate.query(sql, new TouristAttractionRowMapper());

        for (TouristAttraction t : attractions) {
            t.setTags(getTagByAttraction(t.getTouristAttraction_id()));
        }
        return attractions;
    }

    public int addTouristAttraction(TouristAttraction touristAttraction) {
        String sql = """
                INSERT INTO touristattractions (city_id, name, description)
                VALUES((SELECT city_id FROM cities WHERE city = ?), ?, ?)
                """;
        return jdbcTemplate.update(sql, touristAttraction.getCity(), touristAttraction.getName(), touristAttraction.getDescription());
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        String sql = """
                SELECT t.touristAttraction_id, t.name, t.description, c.city AS city
                FROM touristAttractions t
                JOIN cities c ON t.city_id = c.city_id               
                WHERE t.name = ?
                """;

        List<TouristAttraction> result = jdbcTemplate.query(sql,
                (rs, rowNum) -> new TouristAttraction(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("city"),
                        getTagByAttraction(rs.getInt("touristAttraction_id"))
                ), name);
        return result.isEmpty() ? null : result.get(0);
    }

    public int updateTouristAttraction(TouristAttraction t) {

        String sql = """
                UPDATE touristAttractions
                SET city_id = (
                    SELECT city_id
                    FROM cities
                    WHERE city = ?
                ),
                    name = ?,
                    description = ?
                WHERE touristAttraction_id = ?    
                """;

        return jdbcTemplate.update(sql, t.getCity(), t.getName(), t.getDescription(), t.getTouristAttraction_id());
    }

    public void updateTag(TouristAttraction t) {

        int attractionID = t.getTouristAttraction_id();

        String deletedSQL = """
                DELETE
                FROM touristAttractions
                WHERE touristAttraction_id = ?
                """;

        jdbcTemplate.update(deletedSQL, attractionID);

        String insertSQL = """
                INSERT INTO touristAttraction_tags (touristAttraction_id, tag_id)
                SELECT ?, tag_id
                FROM tags
                WHERE tag = ?
                """;

        for (String tag : t.getTags()) {
            int rows = jdbcTemplate.update(insertSQL, attractionID, tag);


            if (rows != 1) {
                throw new IllegalArgumentException(
                        "Tag not found " + tag
                );
            }
        }
    }

    public int deleteTouristAttractionByName(String name) {

        String sql = """
                DELETE 
                FROM touristAttractions 
                WHERE name = ?
                """;
        return jdbcTemplate.update(sql, name);
    }


    public List<String> getTagByAttraction(int touristAttraction_id) {

        String sql = """
                SELECT t.tag
                FROM tags t
                JOIN touristAttraction_tags tat ON tat.tag_id = t.tag_id
                WHERE tat.touristAttraction_id = ? 
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag"), touristAttraction_id);
    }


    public List<String> getTags() {

        String sql = """
                SELECT tag
                FROM tags
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag"));
    }

    public List<String> getCities() {

        String sql = """
                SELECT city
                FROM cities
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("city"));
    }
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
