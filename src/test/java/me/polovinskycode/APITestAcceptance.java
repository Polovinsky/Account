package me.polovinskycode;

import com.google.gson.Gson;
import me.polovinskycode.domain.builder.AccountBuilder;
import me.polovinskycode.domain.model.Account;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class APITestAcceptance {

    private Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        API.main();

        Account account = new AccountBuilder().credit(100d).draw(100d).build();
        given()
                .body(gson.toJson(account))
                .post("http://localhost:4567/api/v1/accounts")
                .then()
                .statusCode(200);
    }

    @Test
    public void limits_withIdValidate_shouldReturnAccount() throws Exception {
        String json =
                given()
                        .get("http://localhost:4567/api/v1/accounts/limits/{id}", 1)
                        .getBody().print();
        Account account = gson.fromJson(json, Account.class);

        assertThat(account.getLimitDraw(), equalTo(100d));
        assertThat(account.getLimitCredit(), equalTo(100d));
    }
}