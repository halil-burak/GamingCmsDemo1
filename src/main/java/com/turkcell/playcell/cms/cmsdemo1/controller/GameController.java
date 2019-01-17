package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.service.GameService;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Object> getGames() {
        logger.info("retrieving games");
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        if (!gameService.retrieveGames().isEmpty()) {
            List<Game> gameList = gameService.retrieveGames();
            for (Game game : gameList) {
                JSONObject json = new JSONObject();
                json.put("id", game.getId());
                json.put("name", game.getName());
                //json.put("type", game.getType());
                json.put("url", game.getUrl());
                json.put("link", game.getGameLink());
                //json.put("size", game.getSize());
                //json.put("environment", game.getEnvironment());
                json.put("isBlockLink", game.isBlockLink());
                json.put("categories", game.getCategoryList());
                json.put("descriptions", game.getDescriptions());
                jsonObjects.add(json);
            }
        }
        return new ResponseEntity<Object>(jsonObjects.toString(), HttpStatus.OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Object> getGame(@PathVariable(name = "gameId") Long gameId) {
        logger.info("retrieving a single game");
        if (gameService.existsById(gameId)) {
            Game game = gameService.retrieveGame(gameId);
            JSONObject json = new JSONObject();
            json.put("id", game.getId());
            json.put("name", game.getName());
            //json.put("type", game.getType());
            json.put("url", game.getUrl());
            json.put("link", game.getGameLink());
            //json.put("size", game.getSize());
            //json.put("environment", game.getEnvironment());
            json.put("isBlockLink", game.isBlockLink());
            json.put("categories", game.getCategoryList());
            json.put("descriptions", game.getDescriptions());

            return new ResponseEntity<Object>(json.toMap(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Object> addGame(@RequestBody Game game) {
        logger.info("adding a game");
        try {
            gameService.saveGame(game);
            JSONObject json = new JSONObject();
            json.put("id", game.getId());
            json.put("name", game.getName());
            //json.put("type", game.getType());
            json.put("url", game.getUrl());
            json.put("gameLink", game.getGameLink());
            //json.put("size", game.getSize());
            //json.put("resize", game.isResize());
            //json.put("environment", game.getEnvironment());
            json.put("isBlockLink", game.isBlockLink());
            json.put("categories", game.getCategoryList());
            json.put("descriptions", game.getDescriptions());
            logger.info("Game added.");

            return new ResponseEntity<Object>(json.toMap(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{gameId}")
    public ResponseEntity updateGame(@RequestBody Game newGame, @PathVariable(name = "gameId") Long id) {
        logger.info("Updating game...");
        if (gameService.existsById(id)) {
            Game game = gameService.retrieveGame(id);

            if (newGame.getName() != null) {
                game.setName(newGame.getName());
            }
            if (newGame.getUrl() != null) {
                game.setUrl(newGame.getUrl());
            }

            gameService.saveGame(game);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity deleteGame(@PathVariable(name = "gameId") Long gameId) {
        logger.info("Deleting a game");
        if (gameService.existsById(gameId)) {
            logger.info("Game removed.");
            gameService.deleteGame(gameId);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.info("Game could not be found!");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
