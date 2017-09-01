package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.model.Account;
import org.hibernate.Session;

public class AccountRepository implements Repository<Account> {

    private Session session;

    public AccountRepository() {

    }

    public AccountRepository(Session session) {
        this.session = session;
    }

    @Override
    public Account find(Long id) {
        return null;
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
