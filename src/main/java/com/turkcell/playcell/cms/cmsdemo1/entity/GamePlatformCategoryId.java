package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GamePlatformCategoryId implements java.io.Serializable {

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "GAME_ID")
    private Game game ;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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
