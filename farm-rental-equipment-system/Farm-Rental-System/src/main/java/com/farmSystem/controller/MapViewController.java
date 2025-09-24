package com.farmSystem.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapViewController {

    @Value("${google.maps.key}")
    private String googleMapsApiKey;
    
    @Value("${map.id}")
    private String mapId;

    @GetMapping("/track")
    public String showTrackingPage(@RequestParam("deliveryId") int deliveryId, Model model) {
        model.addAttribute("apiKey", googleMapsApiKey);
        model.addAttribute("deliveryId", deliveryId);
        model.addAttribute("mapId",mapId);
        return "track"; // loads track.html from templates
    }
}
