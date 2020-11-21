package net.banking.controller;

import static spark.Spark.*;

import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private String userEmail;
    private int userId;

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public Controller(final UserService userService, final AccountService accountService, final TransactionService transactionService){
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "landing.hbs");
        });

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            userEmail = request.queryParams("email");

            User user = userService.selectUser(userEmail);

            if(user != null){
                userId = user.getId();
                response.redirect("/user/" + userId);
            }

            userEmail = null;

            response.redirect("/");

            return "";
        });

        get("/user/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "userDashboard.hbs");
        }));
    }
}
