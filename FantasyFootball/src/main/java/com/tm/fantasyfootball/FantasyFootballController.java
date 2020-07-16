package com.tm.fantasyfootball;

import com.tm.fantasyfootball.model.Player;
import com.tm.fantasyfootball.service.FantasyScoringServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tm.fantasyfootball.service.WebScrapperServiceLayer;
import com.tm.fantasyfootball.model.ScoringScheme;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping({"/"})
public class FantasyFootballController {
    WebScrapperServiceLayer wsService;
    FantasyScoringServiceLayer fsService;
        
    public FantasyFootballController(WebScrapperServiceLayer wsService, FantasyScoringServiceLayer fsService) {
        this.wsService = wsService;
        this.fsService = fsService;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayIndex(Model model) {
        return "index";
    }
    
    @RequestMapping(value="/fantasyRank", method=RequestMethod.GET)
    public String displayFantasyRank(Model model) {
//        model.addAttribute("playerList", fsService.getAllPlayers());
        List<ScoringScheme> scoring = fsService.getAllScoring();
        model.addAttribute("scoring", scoring);
        return "fantasyRank";
    }
    
    @RequestMapping(value="/displayFantasyScoring",method=RequestMethod.GET)
    public String displayFantasyScoring(Model model, HttpServletRequest request) {
        ScoringScheme scoring = fsService.getScoring(Integer.parseInt(request.getParameter("scoreId")));
        List<Player> players = fsService.getAllPlayersWithFantasyPoints(scoring);
        model.addAttribute("playerList", players);
        return "fantasyRank";
    }
    
    @RequestMapping(value="/scrapFantasyStats", method=RequestMethod.GET)
    public String scrapFantasyStats(Model model) {
        model.addAttribute("playerList", fsService.getAllPlayers());
        List<ScoringScheme> scoring = fsService.getAllScoring();
        model.addAttribute("scoring", scoring);
        wsService.scrapWebSite();
        return "redirect:fantasyRank";
    }
    
    
    @RequestMapping(value="/players", method=RequestMethod.GET)
    public String displayPlayers(Model model) {
        List<Character> intialList = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            intialList.add(ch);
        }
        model.addAttribute("initialList", intialList);
//        List<Player> players = fsService.getAllPlayersByNameStarting(initial);
        List<Player> players = fsService.getAllPlayers();
        model.addAttribute("playerList", players);
        return "players";
    }
    
    @RequestMapping(value="players/{initial}", method=RequestMethod.GET)
    public String displayPlayersByInitial(Model model, @PathVariable("initial") char initial) {
        List<Player> players = fsService.getAllPlayersByNameStarting(initial);
        model.addAttribute("playerList", players);
        return "initial";
    }
    
    @RequestMapping(value="player/{playerId}", method=RequestMethod.GET)
    public String displayPlayer(Model model, @PathVariable("playerId") int playerId) {
        model.addAttribute("player", fsService.getPlayerById(playerId));
        return "player";
    }
    
    @RequestMapping(value="/createScoringMatrix", method=RequestMethod.POST)
    public String createScoringMatrix(HttpServletRequest request, Model model) {
        ScoringScheme scoring = new ScoringScheme();
        scoring.setNumTeams(Integer.parseInt(request.getParameter("NumberOfTeams")));
        scoring.setNumQBs(Integer.parseInt(request.getParameter("NumberOfQB")));
        scoring.setNumRBs(Integer.parseInt(request.getParameter("NumberOfRBs")));
        scoring.setNumWR(Integer.parseInt(request.getParameter("NumberOfWRs")));
        scoring.setNumTEs(Integer.parseInt(request.getParameter("NumberOfTEs")));
        scoring.setNumWRT(Integer.parseInt(request.getParameter("NumberOfFlexRWT")));
        scoring.setNumWR(Integer.parseInt(request.getParameter("NumberOfFlexRW")));
        scoring.setNumWT(Integer.parseInt(request.getParameter("NumberOfFlexWT")));
        scoring.setPassYdPrPoint(Integer.parseInt(request.getParameter("PassingYardPerPoint")));
        scoring.setPointPrPsTd(Integer.parseInt(request.getParameter("PointPerPassingTD")));
        scoring.setPointPrInt(Integer.parseInt(request.getParameter("PointPerInterception")));
        scoring.setRushYdPrPoint(Integer.parseInt(request.getParameter("RushingYardPerPoint")));
        scoring.setRushYdPrPoint(Integer.parseInt(request.getParameter("PointPerRushingTD")));
        scoring.setPointPrFmbl(Integer.parseInt(request.getParameter("PointPerFumble")));
        scoring.setRecYdPrPoint(Integer.parseInt(request.getParameter("ReceivingYardPerPoint")));
        scoring.setPointPrRcTd(Integer.parseInt(request.getParameter("PointPerReceivingTD")));
        scoring.setPointPrRec(Integer.parseInt(request.getParameter("PointPerReception")));
        fsService.addScoring(scoring);
        model.addAttribute("scoring", scoring);
        return "redirect:fantasyRank";
    }
}
