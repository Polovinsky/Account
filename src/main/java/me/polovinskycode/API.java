package me.polovinskycode;

import static spark.Spark.get;
import static spark.Spark.port;

public class API {

    public static void main(String... args) {

        port(Integer.valueOf(System.getenv("PORT")));
        get("/hello", (req, res) -> "Hello World");
    }
}
