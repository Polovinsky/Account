package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.model.Account;
import me.polovinskycode.infrastructure.persistence.JPAUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AccountRepositoryIT {

    private AccountRepository repository;

    @Before
    public void setUp() throws Exception {
        Session session = JPAUtil.session();
        repository = new AccountRepository(session);
    }

    @Test
    public void save_withValideObjectAccount_shouldSaveAccount() throws Exception {
        double limitCredit = 100;
        double drawLimit = 100;
        Account account = new Account(limitCredit, drawLimit);
        repository.save(account);

        Account c = repository.findById(1L);
        assertThat(c, notNullValue());
        assertThat(c.getLimitCredit(), equalTo(100d));
        assertThat(c.getLimitDraw(), equalTo(100d));
    }
}