package com.hburak.redis1.redisdemo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> retrieveGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    @Override
    public Game retrieveGame(Long gameId) {
        Game game = gameRepository.getOne(gameId);
        return game;
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.saveAndFlush(game);
    }

    @Override
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}
