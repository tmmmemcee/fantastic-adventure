/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball;

import com.tm.fantasyfootball.service.FantasyScoringServiceLayer;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author tmmmemcee
 */

@Controller

public class PlayerController {
    FantasyScoringServiceLayer fsService;

    @Inject
    public PlayerController(FantasyScoringServiceLayer fsService) {
        this.fsService = fsService;
    }

}
