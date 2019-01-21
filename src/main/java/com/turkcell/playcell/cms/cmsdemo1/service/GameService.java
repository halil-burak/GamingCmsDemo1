package com.turkcell.playcell.cms.cmsdemo1.service;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;

import java.util.List;

public interface GameService {

    public List<Game> retrieveGames();

    public Game retrieveGame(Long gameId);

    public void saveGame(Game game);

    public void deleteGame(Long gameId);

    public boolean existsById(Long gameId);

    public List<Category> retrieveCategoriesOfTheGame(Game game);

    public List<Game> getAllGames();

    public Game findByName(String name);

    public List<Game> getGamesOfCategory(Long categoryId);
}
