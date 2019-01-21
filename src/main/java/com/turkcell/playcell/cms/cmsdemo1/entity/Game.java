package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        /*@OneToMany(mappedBy = "pk.game", cascade = {CascadeType.MERGE})
        private List<PlatformGameCategory> platformGameCategoryLinks;*/

        @JoinTable(name = "GAME_PLATFORM_CATEGORY",
                joinColumns = @JoinColumn(name = "GAME_ID"),// this is for game
                inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")) // this is for category
        @MapKeyJoinColumn(name = "PLATFORM_ID") // this is for the platform
        @ElementCollection
        private Map<Platform, Category> categoriesByPlatform = new HashMap<>();


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

    public Map<Platform, Category> getCategoriesByPlatform() {
        return categoriesByPlatform;
    }

    public void setCategoriesByPlatform(Map<Platform, Category> categoriesByPlatform) {
        this.categoriesByPlatform = categoriesByPlatform;
    }
/*
        public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
            return platformGameCategoryLinks;
        }

        public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
            this.platformGameCategoryLinks = platformGameCategoryLinks;
        }*/
}
