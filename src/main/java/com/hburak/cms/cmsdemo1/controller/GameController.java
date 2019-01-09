package com.hburak.cms.cmsdemo1.controller;

import com.hburak.cms.cmsdemo1.entity.Game;
import com.hburak.cms.cmsdemo1.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GameService gameService;

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("")
    public List<Game> getGames() {
        logger.info("retrieving games");
        List<Game> gameList = gameService.retrieveGames();
        return gameList;
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable(name="gameId")Long gameId) {
        logger.info("retrieving a single game");
        Game game = gameService.retrieveGame(gameId);
        return game;
    }

    @PostMapping("/")
    public void addGame(Game game) {
        logger.info("adding a game");
        gameService.saveGame(game);
        System.out.println("Game added.");
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable(name = "gameId")Long gameId) {
        logger.info("deleting a game");
        gameService.deleteGame(gameId);
        System.out.println("Game removed.");
    }
}
