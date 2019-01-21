package com.turkcell.playcell.cms.cmsdemo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
/*
    @OneToMany(mappedBy = "pk.category", cascade = {CascadeType.MERGE})
    private List<PlatformGameCategory> platformGameCategoryLinks;*/

    @JoinTable(name = "GAME_PLATFORM_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),// this is for category
            inverseJoinColumns = @JoinColumn(name = "PLATFORM_ID")) // this is for platform
    @MapKeyJoinColumn(name = "GAME_ID") // this is for the game
    @ElementCollection
    @JsonIgnore
    private Map<Game, Platform> platformsByGame = new HashMap<>();

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

    public Map<Game, Platform> getPlatformsByGame() {
        return platformsByGame;
    }

    public void setPlatformsByGame(Map<Game, Platform> platformsByGame) {
        this.platformsByGame = platformsByGame;
    }

    /*
    public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
        return platformGameCategoryLinks;
    }

    public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
        this.platformGameCategoryLinks = platformGameCategoryLinks;
    }*/
}
