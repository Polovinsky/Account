package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.builder.AccountBuilder;
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
        Double c = add(limitCredit, account);
        Double d = add(limitDraw, account);
        return new AccountBuilder().credit(c).draw(d).build();
    }

    private Double add(Double limit, Account account) {
        double credit = 0;
        if (limit != null) credit = account.getLimitCredit() + limit;
        return credit;
    }
}
