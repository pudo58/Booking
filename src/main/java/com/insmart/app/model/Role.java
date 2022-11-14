package com.insmart.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 2981402198406440470L;
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "NAME")
    private String name;

    @Column(name = "EFFECT_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectFrom;

    @Column(name = "EFFECT_UNTIL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectUntil;

    @Column(name = "STATUS")
    private Integer status;

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

    public Date getEffectFrom() {
        return effectFrom;
    }

    public void setEffectFrom(Date effectFrom) {
        this.effectFrom = effectFrom;
    }

    public Date getEffectUntil() {
        return effectUntil;
    }

    public void setEffectUntil(Date effectUntil) {
        this.effectUntil = effectUntil;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}