package me.polovinskycode.domain.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "account")
    @GenericGenerator(name = "account", strategy = "increment")
    private long id;

    @Column
    private double limitCredit;

    @Column
    private double limitDraw;

    public Account() {

    }

    public Account(long id, double limitCredit, double limitDraw) {
        this.id = id;
        this.limitCredit = limitCredit;
        this.limitDraw = limitDraw;
    }

    public long getId() {
        return id;
    }

    public double getLimitCredit() {
        return limitCredit;
    }

    public double getLimitDraw() {
        return limitDraw;
    }
}
