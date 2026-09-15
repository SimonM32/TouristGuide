package org.example.touristguide;

import org.example.touristguide.controller.TouristController;
import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @Test
    void shouldReturnAttractions() throws Exception {

        List<TouristAttraction> attractions = List.of(
                new TouristAttraction("Den lille havfrue", "En meget fin statue"),
                new TouristAttraction("Noma", "Der er god mad"),
                new TouristAttraction("Tivoli", "super sjovt"),
                new TouristAttraction("Erhvervsakademi Kobenhavn", "Vores skole")
        );

        when(touristService.getAllTouristAttractions()).thenReturn(attractions);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("attraction", attractions));

        verify(touristService).getAllTouristAttractions();
    }

    @Test
    void shouldAddAttraction() throws Exception {

        TouristAttraction attractions = new TouristAttraction("Tivoli", "super sjovt");


    }
}