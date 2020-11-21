package net.banking;

import net.banking.controller.Controller;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;

public class App
{
    public static void main( String[] args )
    {
        new Controller(new UserService(), new AccountService(), new TransactionService());
    }
}
