package net.banking;

import net.banking.controller.Controller;
import net.banking.models.Account;
import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

import static spark.Spark.staticFiles;

public class App
{
    public static void main( String[] args )
    {
        staticFiles.location("/public");

        new Controller(new UserService(), new AccountService(), new TransactionService());
    }
}
