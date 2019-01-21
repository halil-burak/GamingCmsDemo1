package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PLATFORM_GAME_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"),
            inverseJoinColumns = {
                    @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"),
                    @JoinColumn(name = "PLATFORM_ID", referencedColumnName = "ID")
            })
    private List<PlatformGameCategory> pgcList;*/
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "GAME_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"))
    private List<Game> gameList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    // convenience method to add a single game to a category
    public void addGame(Game game) {
        if (gameList != null) {
            gameList = new ArrayList<>();
        }
        gameList.add(game);
    }
    /*
    public List<PlatformGameCategory> getPgcList() {
        return pgcList;
    }

    public void setPgcList(List<PlatformGameCategory> pgcList) {
        this.pgcList = pgcList;
    }*/
}
