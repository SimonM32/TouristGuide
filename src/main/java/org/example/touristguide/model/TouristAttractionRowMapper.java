package org.example.touristguide.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TouristAttractionRowMapper implements RowMapper<TouristAttraction> {

    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {

        //DETTE VIL OPRETTE FLERE JAVA OBJEKTER END DEN SKAL HVIS EN ATTRACTION HAR MERE END 1 TAG

        String tag = rs.getString("tag");

        List<String> tags = new ArrayList<>();

        if(tag != null) {
            tags.add(tag);
        }

        return new TouristAttraction(
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("city"),
                tags
        );
    }

}
