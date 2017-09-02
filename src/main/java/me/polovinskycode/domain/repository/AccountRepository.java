package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.model.Account;
import me.polovinskycode.infrastructure.persistence.JPAUtil;
import org.hibernate.Session;

import java.util.List;

public class AccountRepository implements Repository<Account> {

    private Session session;

    public AccountRepository() {
        this.session = JPAUtil.getSession();
    }

    public AccountRepository(Session session) {
        this.session = session;
    }

    @Override
    public Account findById(Long id) {
        return session.find(Account.class, id);
    }

    @Override
    public List<Account> findAll() {
        //nothing to do
        return null;
    }

    @Override
    public void save(Account account) {
        session.save(account);
    }

    @Override
    public void update(Account account) {
        session.update(account);
    }

    @Override
    public void delete(Account account) {
        session.delete(account);
    }

    public Account receiveLimit(Long id, Double limitCredit, Double limitDraw) {
        Account account = findById(id);
        account.setLimitCredit(add(account.getLimitCredit(), limitCredit));
        account.setLimitDraw(add(account.getLimitDraw(), limitDraw));
        return account;
    }

    private Double add(Double limit, Double receive) {
        if (receive == null) throw new IllegalArgumentException("Limit not should be null.");
        return limit + receive;
    }
}
