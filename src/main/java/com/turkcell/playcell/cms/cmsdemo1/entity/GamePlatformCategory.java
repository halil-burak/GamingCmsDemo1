package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;

@Entity
@Table(name = "GAME_PLATFORM_CATEGORY")
public class GamePlatformCategory {

    @EmbeddedId
    private GamePlatformCategoryId linkPk = new GamePlatformCategoryId();

    public GamePlatformCategoryId getLinkPk() {
        return linkPk;
    }

    public void setLinkPk(GamePlatformCategoryId linkPk) {
        this.linkPk = linkPk;
    }

    @Transient
    public Game getGame() {
        return getLinkPk().getGame();
    }

    @Transient
    public Category getCategory() {
        return getLinkPk().getCategory();
    }

    @Transient
    public Platform getPlatform() {
        return getLinkPk().getPlatform();
    }
}
