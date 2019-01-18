package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
    @Table(name="GAME")
    public class Game {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="NAME")
        private String name;

        @Column(name="URL")
        private String url;

        @Column(name="LINK")
        private String gameLink;

        @Column(name="BLOCK_LINK")
        private boolean blockLink;

        @Column(name="PUBLISH_WEB")
        private boolean publishWeb;

        @Column(name="PUBLISH_ANDROID")
        private boolean publishAndroid;

        @Column(name="PUBLISH_IOS")
        private boolean publishIos;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "GAME_CATEGORY",
                joinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
        private List<Category> categoryList;

        @OneToMany(mappedBy = "game",
                fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<GameDescription> descriptions;

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<GameDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<GameDescription> description) {
        this.descriptions = description;
    }

    public String getGameLink() {
        return gameLink;
    }

    public void setGameLink(String gameLink) {
        this.gameLink = gameLink;
    }

    public boolean isBlockLink() {
        return blockLink;
    }

    public void setBlockLink(boolean blockLink) {
        this.blockLink = blockLink;
    }

    public boolean isPublishWeb() {
        return publishWeb;
    }

    public void setPublishWeb(boolean publishWeb) {
        this.publishWeb = publishWeb;
    }

    public boolean isPublishAndroid() {
        return publishAndroid;
    }

    public void setPublishAndroid(boolean publishAndroid) {
        this.publishAndroid = publishAndroid;
    }

    public boolean isPublishIos() {
        return publishIos;
    }

    public void setPublishIos(boolean publishIos) {
        this.publishIos = publishIos;
    }
}
