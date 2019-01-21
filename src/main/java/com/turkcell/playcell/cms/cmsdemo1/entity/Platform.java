package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLATFORM")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pk.platform", cascade = {CascadeType.MERGE})
    private List<PlatformGameCategory> platformGameCategoryLinks;

    @Column(name = "NAME")
    private String name;

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

    public List<PlatformGameCategory> getPlatformGameCategoryLinks() {
        return platformGameCategoryLinks;
    }

    public void setPlatformGameCategoryLinks(List<PlatformGameCategory> platformGameCategoryLinks) {
        this.platformGameCategoryLinks = platformGameCategoryLinks;
    }
}
