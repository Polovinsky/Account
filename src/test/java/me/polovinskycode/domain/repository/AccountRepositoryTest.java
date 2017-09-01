package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.model.Account;
import me.polovinskycode.infrastructure.persistence.JPAUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountRepositoryTest {

    private AccountRepository repository;

    @Before
    public void setUp() throws Exception {
        Session session = JPAUtil.session();
        repository = new AccountRepository(session);
    }

    @Test
    public void save_withValideObjectAccount_shouldSaveAccount() throws Exception {
        Account account = null;
        repository.save(account);
    }
}