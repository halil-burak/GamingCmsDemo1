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

        @Column(name="ENVIRONMENT")
        private String environment;

        @Column(name="TYPE")
        private String type;

        @Column(name="LINK")
        private String gameLink;

        @Column(name="BLOCK_LINK")
        private boolean blockLink;

        @Column(name="SIZE")
        private Long size;

        @Column(name="RESIZE")
        private boolean resize;

        /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
        @JoinTable(name = "GAME_CATEGORY",
                joinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
        private List<Category> categoryList;*/
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "GAME_CATEGORY",
                joinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"))
        private List<Category> categoryList;

        /*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "ID")*/
        @OneToMany(mappedBy = "game")
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

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public boolean isResize() {
        return resize;
    }

    public void setResize(boolean resize) {
        this.resize = resize;
    }
}
