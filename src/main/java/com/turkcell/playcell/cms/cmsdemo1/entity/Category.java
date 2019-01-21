package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "pk.category", cascade = {CascadeType.MERGE})
    private List<PlatformGameCategory> platformGameCategoryLinks;

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

    public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
        return platformGameCategoryLinks;
    }

    public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
        this.platformGameCategoryLinks = platformGameCategoryLinks;
    }
}
