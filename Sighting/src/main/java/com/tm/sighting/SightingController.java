package com.tm.sighting;

import com.tm.sighting.model.Location;
import com.tm.sighting.model.Sighting;
import com.tm.sighting.model.SuperHero;
import com.tm.sighting.service.SightingServiceLayer;
import com.tm.sighting.service.SuperServiceLayer;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class SightingController {
    SightingServiceLayer sightingService;
    SuperServiceLayer superService;
    
    @Inject
    public SightingController(SightingServiceLayer sightingService, SuperServiceLayer superService) {
        this.superService = superService;
        this.sightingService = sightingService;
    }
    
    @RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
    public String displayIndex(Model model) {
        List<Sighting> sightings = sightingService.getMostRecentSightings();
        sightings.stream().forEach((Sighting s) -> {
            s.setSuperHero(sightingService.getSuperHeroFromSighting(s.getSightingId()));
            s.getSuperHero().setPicture(superService.getPictureById(s.getSuperHero().getSuperHeroId()));
            s.setLocation(sightingService.findLocationForSighting(s.getSightingId()));
        });
        model.addAttribute("sightings", sightings);
        List<SuperHero> heros = superService.getAllSuperHeros();
        model.addAttribute("superList", heros);
        List<Location> locationList = sightingService.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "index";
    }
    @RequestMapping(value="/createSighting", method=RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {
        Sighting sighting = new Sighting();
        sighting.setSuperHero(superService.getSuper(Long.parseLong(request.getParameter("super"))));
        sighting.setLocation(sightingService.getLocationById(Long.parseLong(request.getParameter("location"))));
        sighting.setDate(LocalDate.parse(request.getParameter("date")));
        sightingService.addSighting(sighting);
        return "redirect:index";
    }
    
    @RequestMapping(value="/deleteSighting", method=RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("sightingId"));
        sightingService.deleteSighting(id);
        return "redirect:index";
    }
    
    @RequestMapping(value="/sighting/{sightingId}", method=RequestMethod.GET) 
    public String displaySighting(Model model, @PathVariable("sightingId") String sightingId) {
        long id = Long.parseLong(sightingId);
        Sighting sighting = sightingService.getSighting(id);
        sighting.setSuperHero(sightingService.getSuperHeroFromSighting(id));
        sighting.setLocation(sightingService.findLocationForSighting(id));
        model.addAttribute("sighting", sighting);
        return "view";
    }
    
    @RequestMapping(value="/displayEditSightingForm", method=RequestMethod.GET)
    public String displayEditSightingForm(Model model, HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("sightingId"));
        Sighting sighting = sightingService.getSighting(id);
        sighting.setSuperHero(sightingService.getSuperHeroFromSighting(id));
        sighting.setLocation(sightingService.findLocationForSighting(id));
        model.addAttribute("sighting", sighting);
        List<SuperHero> heros = superService.getAllSuperHeros();
        model.addAttribute("superList", heros);
        List<Location> locationList = sightingService.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "edit";
    }
    
    @RequestMapping(value="/editSighting", method=RequestMethod.POST)
    public String editSighting(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("sightingId"));
        Sighting sighting = sightingService.getSighting(id);
        sighting.setDate(LocalDate.parse(request.getParameter("date")));
        sighting.setLocation(sightingService.getLocationById(Long.parseLong(request.getParameter("location"))));
        sighting.setSuperHero(superService.getSuper(Long.parseLong(request.getParameter("super"))));
        sightingService.updateSighting(sighting);
        return "redirect:index";
    }
}
