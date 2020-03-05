/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.dto;

/**
 *
 * @author tmmmemcee
 */
public class Player {
    private String name;
    private String team;
    private int exper; // int = experience, 0 being rookie 
    private String pos;
    
    public Player(String name) {
        this.name = name;
    }
    
    
}
