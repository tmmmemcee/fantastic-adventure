/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting;

import com.tm.sighting.model.Location;
import com.tm.sighting.model.Organization;
import com.tm.sighting.model.SuperHero;
import com.tm.sighting.service.SightingServiceLayer;
import com.tm.sighting.service.SuperServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tmmmemcee
 */
@Controller
public class OrganizationController {
    SuperServiceLayer superService;
    SightingServiceLayer sightingService;
    
    @Inject
    public OrganizationController(SightingServiceLayer sightingService, SuperServiceLayer superService) {
        this.sightingService= sightingService;
        this.superService = superService;
    }
    
    @RequestMapping(value={"organizations", "/Organization"}, method=RequestMethod.GET)
    public String displayOrganizations(Model model) {
        List<Location> locs = sightingService.getAllLocations();
        model.addAttribute("locationList", locs);
        List<Organization> orgs = superService.getAllOrganizations();
        superService.findLocationsForListOrganization(orgs);
        model.addAttribute("organizationList", orgs);
        return "organizations";
    }
    @RequestMapping(value="/createOrganization", method=RequestMethod.POST)
    public String createOrganization(HttpServletRequest request) {
        Organization org = new Organization();
        org.setName(request.getParameter("organizationName"));
        org.setDescription(request.getParameter("description"));
        org.setPhone(request.getParameter("organizationPhone"));
        String locationId = request.getParameter("location");
        
        org.setLocation(sightingService.getLocationById(Integer.parseInt(locationId)));
        superService.addOrganization(org);
        return "redirect:organizations";
    }
    
    @RequestMapping(value="/organization/{organizationId}", method=RequestMethod.GET)
    public String dipsplayOrganization(Model model, @PathVariable("organizationId") long id) {
        Organization org = superService.getOrganizationById(id);
        org.setLocation(sightingService.findLocationForOrganization(id));
        model.addAttribute("organization",org);
        List<SuperHero> heros = superService.getAllSuperHerosByOrganization(id);
        model.addAttribute("superList", heros);
        return "view";
    }
    
    @RequestMapping(value="/deleteOrganization", method=RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String id = request.getParameter("organizationId");
        superService.deleteOrganization(Long.parseLong(id));
        return "redirect:organizations";
    }
    
    @RequestMapping(value="/displayEditOrganizationForm", method=RequestMethod.GET) 
    public String displayEditOrganizationForm(Model model, HttpServletRequest request) {
        long orgId = Long.parseLong(request.getParameter("organizationId"));
        Organization org = superService
                .getOrganizationById(orgId);
        org.setLocation(sightingService.findLocationForOrganization(orgId));
        model.addAttribute("locationList", sightingService.getAllLocations());
        model.addAttribute("organization", org);
        return "edit";
    }
    
    @RequestMapping(value="editOrganization", method=RequestMethod.POST)
    public String editOrganization(HttpServletRequest request) {
        Organization org = superService.getOrganizationById(Long.parseLong(request.getParameter("organizationId")));
        org.setName(request.getParameter("name"));
        org.setLocation(sightingService.getLocationById(Long.parseLong(request.getParameter("location"))));
        org.setPhone(request.getParameter("phone"));
        org.setDescription(request.getParameter("description"));
        superService.updateOrganization(org);
        return "redirect:organizations";
        
    }
}
