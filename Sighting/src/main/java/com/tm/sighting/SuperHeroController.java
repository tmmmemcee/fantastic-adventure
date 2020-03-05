package com.tm.sighting;

import com.tm.sighting.model.Organization;
import com.tm.sighting.model.Power;
import com.tm.sighting.model.SuperHero;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tm.sighting.service.SuperServiceLayer;

@Controller
public class SuperHeroController {
    
    SuperServiceLayer superService;
    
    
    @Inject
    public SuperHeroController(SuperServiceLayer superService) {
        this.superService = superService;
    }
    
    
    @RequestMapping(value={"/supers","/SuperHero"}, method=RequestMethod.GET)
    public String displaySupers(Model model) {
        List<Power> powers = superService.getAllPowers();
        List<Organization> orgs = superService.getAllOrganizations();
        model.addAttribute("powerList", powers);
        model.addAttribute("organizationList", orgs);
        List<SuperHero> heros = superService.getAllSuperHeros();
        model.addAttribute("superList", heros);
        return "supers";
    }
    
    @RequestMapping(value="/super/{superHeroId}", method=RequestMethod.GET)
    public String dipsplaySuper(Model model, @PathVariable("superHeroId") long id) {
        SuperHero hero = superService.getSuper(id);
        model.addAttribute("hero", hero);
        return "view";
    }
    @RequestMapping(value="/deleteSuper", method=RequestMethod.GET)
    public String deleteSuper(HttpServletRequest request) {
        String id = request.getParameter("superId");
        superService.deleteSuper(Long.parseLong(id));
        return "redirect:supers";
    }
    @RequestMapping(value="/createSuper", method=RequestMethod.POST)
    public String createSuper(HttpServletRequest request,                     
                    Model model,
                    @RequestParam("superHeroName") String displayTitle,
                    @RequestParam("picture") MultipartFile pictureFile) {

        SuperHero hero = new SuperHero();
        hero.setSuperHeroName(request.getParameter("superHeroName"));
        List<Organization> orgs = superService.getAllOrganizationsFromIdArray(request.getParameterValues("organization"));
        superService.findLocationsForListOrganization(orgs);
        hero.setOrganizations(orgs);
        hero.setSuperPowers(superService.getAllPowersFromIdArray(request.getParameterValues("power")));
        hero.setUsePowerForGood(Boolean.getBoolean(request.getParameter("usePowerForGood")));
        hero.setDescription(request.getParameter("description"));
        superService.addSuper(hero);
        superService.uploadPhoto(displayTitle, pictureFile, hero, model);
        return "redirect:supers";
    }
    
    @RequestMapping(value="/displayEditSuperForm", method=RequestMethod.GET) 
    public String displayEditSuperForm(Model model, HttpServletRequest request) {
        SuperHero hero = superService.getSuper(Long.parseLong(request.getParameter("superId")));
        model.addAttribute("organizationList", superService.getAllOrganizations());
        model.addAttribute("hero", hero);
        model.addAttribute("powerList", superService.getAllPowers());
        return "edit";
    }
    
    @RequestMapping(value="editSuper", method=RequestMethod.POST)
    public String editSuper(HttpServletRequest request, Model model,
            @RequestParam("superHeroName") String displayTitle,
            @RequestParam("picture") MultipartFile pictureFile) {
        SuperHero hero = new SuperHero();
        hero.setSuperHeroId(Long.parseLong(request.getParameter("superHeroId")));
        hero.setSuperHeroName(request.getParameter("superHeroName"));
        hero.setUsePowerForGood(Boolean.parseBoolean(request.getParameter("usePowerForGood")));
        hero.setDescription(request.getParameter("description"));
        hero.setSuperPowers(superService.getAllPowersFromIdArray(request.getParameterValues("power")));
        hero.setOrganizations(superService.getAllOrganizationsFromIdArray(request.getParameterValues("organization")));
        if (!pictureFile.isEmpty()) {
            superService.uploadPhoto(displayTitle, pictureFile, hero, model);
        }   
        superService.updateSuper(hero);
        return "redirect:supers";
        
    }
    
}
