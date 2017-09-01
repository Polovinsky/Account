package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.builder.AccountBuilder;
import me.polovinskycode.domain.model.Account;
import me.polovinskycode.infrastructure.persistence.JPAUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AccountRepositoryIT {

    private AccountRepository repository;

    @Before
    public void setUp() throws Exception {
        Session session = JPAUtil.getSession();
        repository = new AccountRepository(session);
    }

    @Test
    public void save_withValideObjectAccount_shouldSaveAccount() throws Exception {
        Account account = new AccountBuilder().credit(100d).draw(100d).build();
        repository.save(account);

        Account c = repository.findById(1L);
        assertThat(c, notNullValue());
        assertThat(c.getLimitCredit(), equalTo(100d));
        assertThat(c.getLimitDraw(), equalTo(100d));
    }

    @Test
    public void findById_withIdNotExistis_shouldReturnNull() throws Exception {
        Account account = repository.findById(1L);
        assertThat(account, nullValue());
    }

    @Test
    public void findAll_shouldReturnAccounts() throws Exception {
        List<Account> accounts = repository.findAll();
    }
}