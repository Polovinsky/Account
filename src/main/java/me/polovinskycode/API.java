package me.polovinskycode;

import java.util.logging.Logger;

import static java.lang.System.getenv;
import static spark.Spark.*;

public class API {

    private static Logger log = Logger.getLogger(API.class.getName());

    public static void main(String... args) {
        port(Integer.valueOf(
                getenv("PORT") == null ? "4567" : getenv("PORT")));

        path("/api", () -> {
            before("/*", (q, a) -> log.info("Received api call"));
            get("/hello/:name", (q, a) -> {
                return "Hello: " + q.params(":name");
            });
        });
    }
}
