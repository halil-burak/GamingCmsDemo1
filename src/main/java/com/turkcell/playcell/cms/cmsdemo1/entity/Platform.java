package com.turkcell.playcell.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PLATFORM")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    // GAME-PLATFORM-CATEGORY MAPPING
    @OneToMany(mappedBy = "linkPk.platform", cascade = CascadeType.MERGE)
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

    public Set<GamePlatformCategory> getPgcLinks() {
        return pgcLinks;
    }

    public void setPgcLinks(Set<GamePlatformCategory> pgcLinks) {
        this.pgcLinks = pgcLinks;
    }
}
