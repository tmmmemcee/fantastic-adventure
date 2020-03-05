/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting;

import com.tm.sighting.model.Location;
import com.tm.sighting.service.SightingServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tmmmemcee
 */
@Controller
public class LocationController {
    SightingServiceLayer sightingService;
    
    @Inject
    public LocationController(SightingServiceLayer sightingService){
        this.sightingService= sightingService;
    }
    
    @RequestMapping(value={"/locations", "/Location"}, method=RequestMethod.GET)
    public String displayLocations(Model model) {
        List<Location> locs = sightingService.getAllLocations();
        model.addAttribute("locationList", locs);
        
        return "locations";
    }
    @RequestMapping(value="/location/{locationId}", method=RequestMethod.GET)
    public String dipsplayLocation(Model model, @PathVariable("locationId") long id) {
        Location location = sightingService.getLocationById(id);
        model.addAttribute("location",location);
        return "view";
    }
    @RequestMapping(value="/deleteLocation", method=RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String id = request.getParameter("locationId");
        sightingService.deleteLocation(Long.parseLong(id));
        return "redirect:locations";
    }
    @RequestMapping(value="/createLocation", method=RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {
        Location loc = new Location();
        loc.setAddress(request.getParameter("address"));
        loc.setName(request.getParameter("locationName"));
        loc.setLatitude(request.getParameter("latitude"));
        loc.setLongitude(request.getParameter("longitude"));
        loc.setDescription(request.getParameter("description"));
        sightingService.addLocation(loc);
        return "redirect:locations";
    }
    @RequestMapping(value="displayEditLocationForm", method=RequestMethod.GET)
    public String displayEditLocation(Model model, HttpServletRequest request) {
        Location location = sightingService.getLocationById(Long.parseLong(request.getParameter("locationId")));
        model.addAttribute("location", location);
        return "edit";
    }
    @RequestMapping(value="editLocation", method=RequestMethod.POST)
    public String editLocation(@ModelAttribute("location") Location location , BindingResult result) {
        if (result.hasErrors()) {
            return "displayEditLocationForm?locationId="+location.getLocationId();
        } else {
            sightingService.updateLocation(location);
            return "redirect:locations";
        }
    
    }
}
