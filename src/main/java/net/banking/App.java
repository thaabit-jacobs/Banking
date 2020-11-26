package net.banking;

import net.banking.controller.Controller;
import net.banking.models.Account;
import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class App
{

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main( String[] args )
    {

        port(getHerokuAssignedPort());

        staticFiles.location("/public");

        new Controller(new UserService(), new AccountService(), new TransactionService());
    }
}



