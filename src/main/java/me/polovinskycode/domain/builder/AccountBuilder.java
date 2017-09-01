package me.polovinskycode.domain.builder;

import me.polovinskycode.domain.model.Account;

public class AccountBuilder {

    private Double limitCredit;
    private Double limitDraw;

    public AccountBuilder credit(Double limitCredit) {
        this.limitCredit = limitCredit;
        return this;
    }

    public AccountBuilder draw(Double limitDraw) {
        this.limitDraw = limitDraw;
        return this;
    }

    public Account build() {
        return new Account(limitCredit, limitDraw);
    }
}
