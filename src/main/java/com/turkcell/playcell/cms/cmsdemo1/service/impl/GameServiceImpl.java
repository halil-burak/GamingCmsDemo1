package com.turkcell.playcell.cms.cmsdemo1.service.impl;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import com.turkcell.playcell.cms.cmsdemo1.entity.GamePlatformCategory;
import com.turkcell.playcell.cms.cmsdemo1.repo.CategoryRepository;
import com.turkcell.playcell.cms.cmsdemo1.repo.GameRepository;
import com.turkcell.playcell.cms.cmsdemo1.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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


    @Override
    public List<Category> retrieveCategoriesOfTheGame(Game game) {
        /*List<Category> categories = new ArrayList<>();
        if (!game.getCategories().isEmpty()) {
            for (Category c : game.getCategories()) {
                categories.add(c);
            }
        }
        return categories;*/
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAllActiveGames();
    }

    @Override
    public Game findByName(String name) {
        return gameRepository.findByName(name);
    }

    @Override
    public List<Game> findGamesByCategoryId(Long categoryId) {
        //return gameRepository.findGamesByCategoryId(categoryId);
        return null;
    }

    /*@Override
    public List<Game> getGamesOfCategory(Long categoryId) {
        return gameRepository.getGamesOfCategory(categoryId);
    }*/


}
