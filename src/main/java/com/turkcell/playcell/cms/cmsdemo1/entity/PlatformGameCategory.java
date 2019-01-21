package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;

//@Entity
//@Table(name = "PLATFORM_GAME_CATEGORY")
public class PlatformGameCategory {
    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game ;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public PlatformGameCategory(){
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
