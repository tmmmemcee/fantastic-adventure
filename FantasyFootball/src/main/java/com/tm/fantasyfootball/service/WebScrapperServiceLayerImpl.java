/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.fantasyfootball.service;


import com.tm.fantasyfootball.dao.PlayerDao;
import com.tm.fantasyfootball.dao.SeasonDao;
import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.model.QB;
import com.tm.fantasyfootball.model.RB;
import com.tm.fantasyfootball.model.Season;
import com.tm.fantasyfootball.model.TE;
import com.tm.fantasyfootball.model.WR;
import java.io.IOException;
import javax.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author tmmmemcee
 */
public class WebScrapperServiceLayerImpl implements WebScrapperServiceLayer{
    PlayerDao playerDao;
    SeasonDao seasonDao;

    @Inject
    public WebScrapperServiceLayerImpl(PlayerDao playerDao, SeasonDao seasonDao) {
        this.playerDao = playerDao;
        this.seasonDao = seasonDao;
    }
    
    
    
    private void scrapSeason(String url, String cssSelector, String year) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements rows = doc.select(cssSelector);
        
        for (int i=0; i<=50;i++){
            Element row = rows.get(i);
            if (!(row.child(2).hasClass("sort_default_asc") || row.child(3).text().isEmpty())) {
                
                String name = row.child(1).text().replaceAll("[^a-zA-Z]+", "");
                
                Player player;
                player = playerDao.getPlayerByName(name);
                String pos = row.child(3).text();
                if (player==null){
                    if (pos.equalsIgnoreCase("qb")) {
                        player = new QB();
                    }
                    else if (pos.equalsIgnoreCase("rb")) {
                        player = new RB();
                    }
                    else if (pos.equalsIgnoreCase("wr")) {
                                player = new WR();
                    }
                    else if (pos.equalsIgnoreCase("te")) {
                                    player = new TE();
                    }
                    else {
                        player = new Player();
                    }
                    
                    player.setName(name);
                    if (name.equals("TravisKelce")) {
                        playerDao.addPlayer(player);
                    } else {
                        playerDao.addPlayer(player);
                    }
                    
                    ScrapFantasyRow(row, player, year);
                } else {
                    Season season =seasonDao.getSeason(player.getPlayerId(),Integer.parseInt(year));
                    if (season==null) {
                        ScrapFantasyRow(row, player, year);
                    }
                }
            }
        }
    }
    
    public void ScrapFantasyRow(Element row, Player player, String year) {
        Season season = new Season();
        season.setAge(Integer.parseInt(row.child(4).text()));
        season.setYear(Integer.parseInt(year));
        season.setGames(Integer.parseInt(row.child(5).text()));
        season.setGameStarted(Integer.parseInt(row.child(6).text()));
        season.setCompletions(Integer.parseInt(row.child(7).text()));
        season.setPassAttempts(Integer.parseInt(row.child(8).text()));
        season.setPassYards(Integer.parseInt(row.child(9).text()));
        season.setPassTds(Integer.parseInt(row.child(10).text()));
        season.setInters(Integer.parseInt(row.child(11).text()));
        season.setRushAttempts(Integer.parseInt(row.child(12).text()));
        season.setRushYards(Integer.parseInt(row.child(13).text()));
        season.setRushTds(Integer.parseInt(row.child(15).text()));
//        season.setYardPerAttempt(season.getRushYards()/season.getRushAttempts());
        season.setTargets(Integer.parseInt(row.child(16).text()));
        season.setReceptions(Integer.parseInt(row.child(17).text()));
        season.setRecYards(Integer.parseInt(row.child(18).text()));
        season.setRecTds(Integer.parseInt(row.child(20).text()));
//        season.setYardPerCatch(season.getRecYards()/season.getReceptions());
        season.setFumbles(Integer.parseInt(row.child(21).text()));
//        season.setFumblesLost(Integer.parseInt(row.child(22).text()));
//        season.setTwoPointConverstions(Integer.parseInt(row.child(24).text()));
        seasonDao.addSeason(player,season);
    }

    @Override
    public void scrapWebSite() {
        String baseUrl = "https://www.pro-football-reference.com/years/";
        String cssFantasySelector = "#fantasy tbody tr";
        for (int j =1; j>=0; --j){
            for (int i = 9; i>=0; --i) {
                String year = "20"+j+i;
                String fantasyUrl = baseUrl+year+"/fantasy.htm";
                try {
                    scrapSeason(fantasyUrl, cssFantasySelector, year);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    
    
    
    
    
    
}
