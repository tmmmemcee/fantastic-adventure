/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting;

import com.tm.sighting.model.Power;
import com.tm.sighting.service.SuperServiceLayer;
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
public class PowerController {
    SuperServiceLayer superService;
    
    @Inject
    public PowerController(SuperServiceLayer superService) {
        this.superService=superService;
    }
    
    @RequestMapping(value="/power/{powerId}", method=RequestMethod.GET)
    public String displayPower(Model model, @PathVariable("powerId") long id) {
        Power power = superService.getPowerById(id);
        model.addAttribute("power",power);
        return "view";
    }
    @RequestMapping(value={"/powers","/SuperPower"}, method=RequestMethod.GET)
    public String displayPowers(Model model) {
        List<Power> powers = superService.getAllPowers();
        model.addAttribute("powerList", powers);
        return "powers";
    }
    @RequestMapping(value="/createPower", method=RequestMethod.POST)
    public String createPower(HttpServletRequest request) {
        Power power = new Power();
        power.setPowerName(request.getParameter("superPowerName"));
        power.setDescription(request.getParameter("description"));
        superService.addPower(power);
        return "redirect:powers";
    }
    @RequestMapping(value="/deletePower", method=RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        String id = request.getParameter("powerId");
        superService.deletePower(Long.parseLong(id));
        return "redirect:powers";
    }    
    @RequestMapping(value="/displayEditPowerForm", method=RequestMethod.GET)
    public String displayEditPowerForm(Model model, HttpServletRequest request) {
        Power power = superService
                .getPowerById(Long.parseLong(request.getParameter("powerId")));
        model.addAttribute("power", power);
        return "edit";
    }
    
    @RequestMapping(value="/editPower", method=RequestMethod.POST)
    public String editPower(@ModelAttribute("power") Power power , BindingResult result) {
        if (result.hasErrors()) {
            return "displayEditPowerForm?powerId="+power.getPowerId();
        } else {
            superService.updatePower(power);
            return "redirect:powers";
        }
    }
}
