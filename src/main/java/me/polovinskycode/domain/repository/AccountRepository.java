package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.model.Account;
import me.polovinskycode.infrastructure.persistence.JPAUtil;
import org.hibernate.Session;

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
}
