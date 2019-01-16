package com.hburak.cms.cmsdemo1.entity;

import javax.persistence.*;

@Entity
@Table(name = "PLATFORM_GAME_CATEGORY")
public class PlatformGameCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
