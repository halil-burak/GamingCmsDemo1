package com.turkcell.playcell.cms.cmsdemo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "PLATFORM")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/*
    @OneToMany(mappedBy = "pk.platform", cascade = {CascadeType.MERGE})
    private List<PlatformGameCategory> platformGameCategoryLinks;*/

    @JoinTable(name = "GAME_PLATFORM_CATEGORY",
            joinColumns = @JoinColumn(name = "PLATFORM_ID"),// this is for platform
            inverseJoinColumns = @JoinColumn(name = "GAME_ID")) // this is for game
    @MapKeyJoinColumn(name = "CATEGORY_ID") // this is for the category
    @ElementCollection
    @JsonIgnore
    private Map<Category, Game> gamesByCategory = new HashMap<>();

    @Column(name = "NAME")
    private String name;

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

    public Map<Category, Game> getGamesByCategory() {
        return gamesByCategory;
    }

    public void setGamesByCategory(Map<Category, Game> gamesByCategory) {
        this.gamesByCategory = gamesByCategory;
    }
/*
    public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
        return platformGameCategoryLinks;
    }

    public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
        this.platformGameCategoryLinks = platformGameCategoryLinks;
    }*/
}
