package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;

@Entity
@Table(name = "PLATFORM_GAME_CATEGORY")
public class PlatformGameCategory {
    @EmbeddedId
    private PlatformGameCategoryId pk = new PlatformGameCategoryId();

    @Transient
    public Game getGame() {
        return getPk().getGame();
    }

    @Transient
    public Category getCategory() {
        return getPk().getCategory();
    }

    @Transient
    public Platform getPlatform() {
        return getPk().getPlatform();
    }

    public PlatformGameCategory(){
    }

    public PlatformGameCategoryId getPk() {
        return pk;
    }

    public void setPk(PlatformGameCategoryId pk) {
        this.pk = pk;
    }
}
