package org.example.touristguide.controller;

import org.example.touristguide.model.TouristAttraction;
import org.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    @Controller
    public class TouristController {
        private final TouristService service;

        public TouristController(TouristService touristService) {
            this.service = touristService;
        }

        @GetMapping("/attractions/{name}/tags")
        public String tags(Model model, @PathVariable String name) {
            model.addAttribute("attraction", service.getTouristAttractionByName(name));
            return "tags";
        }

        @GetMapping("attractions")
        public String getAttractions(Model model) {
            model.addAttribute("attractions", service.getAllTouristAttractions());
            return "attractionList";
        }

//develop



        @GetMapping("/attractions/{name}")
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

    @PutMapping("/update/{name}")
    public ResponseEntity<TouristAttraction> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction touristAttraction) {

        TouristAttraction updatedAttraction = service.updateTouristAttractionByName(name, touristAttraction.getName(), touristAttraction.getDescription());
        return ResponseEntity.ok(updatedAttraction);
    }

        @DeleteMapping("delete/{name}")
        public ResponseEntity<TouristAttraction> deleteTouristAttractionByName(@PathVariable String name) {
            TouristAttraction deletedAttraction = service.deleteTouristAttractionByName(name);
            if(deletedAttraction != null) {
                return ResponseEntity.ok(deletedAttraction);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
