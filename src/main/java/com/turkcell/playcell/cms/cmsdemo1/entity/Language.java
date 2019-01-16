package com.hburak.cms.cmsdemo1.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LANGUAGE")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCAL_NAME")
    private String localName;

    /*@Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "CREATE_DATE")
    private Date createDate;*/

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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /*
    public boolean isActive() {
        return     isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }*/
}
