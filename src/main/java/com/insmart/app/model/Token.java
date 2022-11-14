package com.insmart.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {
    private static final long serialVersionUID = -8996453102641342607L;
    @Id
    @Column(name = "token_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "effect_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectFrom;

    @Column(name = "effect_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectUntil;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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