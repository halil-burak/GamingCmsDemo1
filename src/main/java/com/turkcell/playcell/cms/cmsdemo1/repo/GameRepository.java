package com.turkcell.playcell.cms.cmsdemo1.repo;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.name = 'string'")
    List<Game> findAllActiveGames();

    /**
     * Custom query, you do not need to write a manual query,
     * hibernate detects that there is a field by the name "name" in Game class
     * @param name
     * @return
     */
    Game findByName(String name);

    /**
     * retrieve categories of a game
     * @param gameId
     * @return
     */

    /*
    @Query("SELECT g FROM Game g JOIN g.categoryList c WHERE c.id = ?1")
    List<Game> getGamesOfCategory(Long categoryId);*/

    /*
    @Query("SELECT g FROM Game g JOIN GamePlatformCategory gpc WHERE gpc.category = :cId")
    List<Game> findGamesByCategoryId(@Param("cId") Long categoryId);*/
}
