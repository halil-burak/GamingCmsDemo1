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

        @OneToMany(mappedBy = "pk.game", cascade = {CascadeType.MERGE})
        private List<PlatformGameCategory> platformGameCategoryLinks;

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

        public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
            return platformGameCategoryLinks;
        }

        public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
            this.platformGameCategoryLinks = platformGameCategoryLinks;
        }
}
