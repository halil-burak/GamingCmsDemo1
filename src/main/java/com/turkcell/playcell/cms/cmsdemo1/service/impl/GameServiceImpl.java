package com.turkcell.playcell.cms.cmsdemo1.service.impl;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import com.turkcell.playcell.cms.cmsdemo1.entity.PlatformGameCategory;
import com.turkcell.playcell.cms.cmsdemo1.repo.GameRepository;
import com.turkcell.playcell.cms.cmsdemo1.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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

    @Override
    public boolean existsById(Long gameId) {
        return gameRepository.existsById(gameId);
    }
}
