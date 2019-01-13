package com.hburak.cms.cmsdemo1.entity;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GAME_CATEGORY",
    joinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "CTGRY_ID", referencedColumnName = "ID"))
    private List<Category> categoryList;

    @Column(name = "SOURCE")
    private String source;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    private GameDescription description;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public GameDescription getDescription() {
        return description;
    }

    public void setDescription(GameDescription description) {
        this.description = description;
    }
}
