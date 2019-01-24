package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    // GAME-PLATFORM-CATEGORY MAPPING
    @OneToMany(mappedBy = "linkPk.category", cascade = CascadeType.MERGE)
    private Set<GamePlatformCategory> pgcLinks;

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

    public Set<GamePlatformCategory> getPgcLinks() {
        return pgcLinks;
    }

    public void setPgcLinks(Set<GamePlatformCategory> pgcLinks) {
        this.pgcLinks = pgcLinks;
    }
}
