package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "LINK")
    private String gameLink;

    @Column(name = "BLOCK_LINK")
    private boolean blockLink;

    @Column(name = "PUBLISH_WEB")
    private boolean publishWeb;

    @Column(name = "PUBLISH_ANDROID")
    private boolean publishAndroid;

    @Column(name = "PUBLISH_IOS")
    private boolean publishIos;

    // GAME-PLATFORM-CATEGORY MAPPING
    @OneToMany(mappedBy = "linkPk.game", cascade = CascadeType.MERGE)
    private Set<GamePlatformCategory> pgcLinks;

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

    public Set<GamePlatformCategory> getPgcLinks() {
        return pgcLinks;
    }

    public void setPgcLinks(Set<GamePlatformCategory> pgcLinks) {
        this.pgcLinks = pgcLinks;
    }
}
