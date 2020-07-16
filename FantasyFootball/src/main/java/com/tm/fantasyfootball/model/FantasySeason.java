/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.model;

/**
 *
 * @author tmmmemcee
 */
public class FantasySeason {
    Season season;
    ScoringScheme scoring;
    float total;

    public FantasySeason(Season season, ScoringScheme scoring) {
        this.season = season;
        this.scoring = scoring;
        this.total = season.passYards/scoring.passYdPrPoint + scoring.pointPrPsTd*season.passTds + scoring.pointPrInt*season.inters +
                season.rushYards/scoring.rushYdPrPoint + scoring.pointPrRsTd*season.rushTds + scoring.pointPrFmbl * season.fumbles +
                season.recYards/scoring.recYdPrPoint + scoring.pointPrRcTd*season.recTds + scoring.pointPrRec*season.receptions ;
    }

}
