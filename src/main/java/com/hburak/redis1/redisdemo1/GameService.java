package com.hburak.redis1.redisdemo1;

import java.util.List;

public interface GameService {

    public List<Game> retrieveGames();

    public Game retrieveGame(Long gameId);

    public void saveGame(Game game);

    public void deleteGame(Long gameId);
}
