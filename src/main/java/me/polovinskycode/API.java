package me.polovinskycode;

import com.google.gson.Gson;
import me.polovinskycode.domain.builder.AccountBuilder;
import me.polovinskycode.domain.model.Account;
import me.polovinskycode.domain.repository.AccountRepository;
import me.polovinskycode.infrastructure.transformer.JsonTransformer;

import static java.lang.System.getenv;
import static spark.Spark.*;

public class API {

    public static void main(String... args) {
        port(Integer.valueOf(getenv("PORT") == null ? "4567" : getenv("PORT")));

        path("/api", () -> {
            AccountRepository accountRepository = new AccountRepository();

            before("/*", (q, a) -> a.header("Access-Control-Allow-Origin", "*"));

            get("/v1/accounts/limits/:id", (q, a) -> {
                String idAccount = q.params(":id");
                return accountRepository.findById(Long.valueOf(idAccount));
            }, new JsonTransformer());

            patch("/v1/accounts/:id", "application/json", (q, a) -> {
                String idAccount = q.params(":id");
                Gson gson = new Gson();
                String json = q.body();
                Account c = gson.fromJson(json, Account.class);
                Account account = accountRepository.receiveLimit(Long.valueOf(idAccount), c.getLimitCredit(), c.getLimitDraw());
                accountRepository.update(account);
                return "201";
            });

            post("/v1/accounts", "application/json", (q, a) -> {
                Gson gson = new Gson();
                String json = q.body();
                Account c = gson.fromJson(json, Account.class);
                Account account = new AccountBuilder()
                        .credit(c.getLimitCredit())
                        .draw(c.getLimitDraw())
                        .build();
                accountRepository.save(account);
                return "201";
            });
        });
    }
}
