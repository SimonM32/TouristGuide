package org.example.touristguide.controller;

import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @Controller
    @RequestMapping("attractions")


    public class TouristController {
        private final TouristService service;

        public TouristController(TouristService touristService) {
            this.service = touristService;
        }

        @GetMapping()
        public ResponseEntity<List<TouristAttraction>> getAttractions() {
            List<TouristAttraction> attractions = service.getAllTouristAttractions();
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        }
        @GetMapping("{name}")
        public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name){
            TouristAttraction attractions = service.getTouristAttractionByName(name);
            if (attractions != null) {
                return new ResponseEntity<>(attractions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        @PostMapping("add")
        public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction attraction){
             service.addTouristAttraction(attraction);
             return ResponseEntity.status(201).body(attraction);
        }
    }
