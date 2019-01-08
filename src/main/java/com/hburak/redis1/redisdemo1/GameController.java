package com.hburak.redis1.redisdemo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/games")
    public List<Game> getGames() {
        List<Game> gameList = gameService.retrieveGames();
        return gameList;
    }

    @GetMapping("/api/games/{gameId}")
    public Game getGame(@PathVariable(name="gameId")Long gameId) {
        Game game = gameService.retrieveGame(gameId);
        return game;
    }

    @PostMapping("/api/games")
    public void addGame(Game game) {
        gameService.saveGame(game);
        System.out.println("Game added.");
    }

    @DeleteMapping("/api/games/{gameId}")
    public void deleteGame(@PathVariable(name = "gameId")Long gameId) {
        gameService.deleteGame(gameId);
        System.out.println("Game removed.");
    }
}
