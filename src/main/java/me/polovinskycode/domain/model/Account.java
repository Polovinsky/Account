package me.polovinskycode.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(generator = "account")
    @GenericGenerator(name = "account", strategy = "increment")
    private Long id;

    @Column
    private Double limitCredit;

    @Column
    private Double limitDraw;

    Account() {

    }

    public Account(Double limitCredit, Double limitDraw) {
        this.limitCredit = limitCredit;
        this.limitDraw = limitDraw;
    }

    public Long getId() {
        return id;
    }

    public Double getLimitCredit() {
        return limitCredit;
    }

    public Double getLimitDraw() {
        return limitDraw;
    }

    public void setLimitCredit(Double limitCredit) {
        this.limitCredit = limitCredit;
    }

    public void setLimitDraw(Double limitDraw) {
        this.limitDraw = limitDraw;
    }
}
