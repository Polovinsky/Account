package me.polovinskycode.domain.repository;

import me.polovinskycode.domain.builder.AccountBuilder;
import me.polovinskycode.domain.model.Account;
import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class AccountRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private AccountRepository repository;
    private Session session;

    @Before
    public void setUp() throws Exception {
        session = Mockito.mock(Session.class);
        repository = new AccountRepository(session);
    }

    @Test
    public void receiveLimit_withValuesPositives_shouldReturnSumofValues() throws Exception {
        Long id = 1L;
        Double limitCredit = 10d;
        Double LimitDraw = 10d;

        Account account = new AccountBuilder().draw(100d).credit(100d).build();
        when(session.find(Account.class, id)).thenReturn(account);

        Account c = repository.receiveLimit(id, limitCredit, LimitDraw);
        assertThat(c.getLimitCredit(), Matchers.is(110d));
        assertThat(c.getLimitDraw(), Matchers.is(110d));
    }

    @Test
    public void receiveLimit_withValuesNegatives_shouldReturnSubtractionOfValues() throws Exception {
        Long id = 1L;
        Double limitCredit = -10d;
        Double LimitDraw = -10d;

        Account account = new AccountBuilder().draw(100d).credit(100d).build();
        when(session.find(Account.class, id)).thenReturn(account);

        Account c = repository.receiveLimit(id, limitCredit, LimitDraw);
        assertThat(c.getLimitCredit(), Matchers.is(90d));
        assertThat(c.getLimitDraw(), Matchers.is(90d));
    }

    @Test
    public void receiveLimit_withValuesNull_shouldReturnException() throws Exception {
        Long id = 1L;
        Double limitCredit = null;
        Double LimitDraw = null;

        Account account = new AccountBuilder().draw(100d).credit(100d).build();
        when(session.find(Account.class, id)).thenReturn(account);

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Matchers.equalTo("Limit not should be null."));
        repository.receiveLimit(id, limitCredit, LimitDraw);
    }
}