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
/*        new AccountService().insertAccount(new Account(2, UUID.fromString("7073e3c6-952e-4e89-b1ac-1b5899684121"), "SAVINGS", 22097.55, LocalDateTime.now(), 1));
        new AccountService().insertAccount(new Account(3, UUID.fromString("18f1daac-b606-4589-82df-756a600476a4"), "SAVINGS", 0.00, LocalDateTime.now(), 1));
        new AccountService().insertAccount(new Account(4, UUID.fromString("6edb0598-5576-437a-8166-c9b406ab277e"), "SAVINGS", 500.25, LocalDateTime.now(), 1));
        new AccountService().insertAccount(new Account(5, UUID.fromString("9a2f4a1d-2165-406a-be02-d4031acf8dcf"), "SAVINGS", 1500.00, LocalDateTime.now(), 1));
        new AccountService().insertAccount(new Account(6, UUID.fromString("99f598b7-2bda-49f4-a40f-06b77152e1c6"), "SAVINGS", 0.50, LocalDateTime.now(), 1));*/

       //new UserService().insertUser(new User(4, "Malakai", "Mathis", "mathis@gmail", LocalDateTime.now()));
        new Controller(new UserService(), new AccountService(), new TransactionService());
    }
}
