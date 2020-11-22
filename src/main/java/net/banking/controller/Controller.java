package net.banking.controller;

import static spark.Spark.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.banking.action.TransactionAction;
import net.banking.exceptions.InsufficientFundsException;
import net.banking.json.ObjectToJson;
import net.banking.models.Account;
import net.banking.models.Transaction;
import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;
import net.banking.types.TransactionType;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.*;

public class Controller {

    private String userEmail;
    private int userId;
    private Double transactionAmount;

    private TransactionAction transactionAction = TransactionAction.getInstance();

    private Transaction transaction;

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public Controller(final UserService userService, final AccountService accountService, final TransactionService transactionService){

        //LOGIN ROUTES
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

        get("/user/logout", ((request, response) -> {
            userEmail = null;
            userId = 0;
            transactionAmount = 0.00;
            transaction = null;

            response.redirect("/");

            return "";
        }));

        //USER DASHBAORDROUTES

        get("/user/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "userDashbaord.hbs");
        }));


        //100

        get("/user/withdrawal/100/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            model.put("amount", 100);

            return render(model, "accountForm.hbs");
        }));

        //STATEMENTS
        get("/user/account/statement", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            return render(model, "accountFormStatement.hbs");
        }));

        post("/user/account/statement", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                List<Transaction> transacs = transactionService.selectAllTransactionForAccount(account.getId());
                System.out.println(transacs);
                model.put("transacs", transacs);
                return render(model, "statement.hbs");
            }

            return render(model, "userDashbaord.hbs");
        }));

        //WITHDRAWALS ROUTES

        //WITHDRAWAL 100
        post("/user/withdrawal/100/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                   transaction = transactionAction.withDrawal(account, 100).setId(transactionService.getUniqueTransactionId());
                    System.out.println(transaction);

                   accountService.updateAccount(account);
                   transactionService.insertTransaction(transaction);

                   response.redirect("/user/" + userId);
                }catch(InsufficientFundsException ife){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.WITHDRAWAL.toString(), 100, false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));

        //WITHDRAWAL 150
        get("/user/withdrawal/150/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);
            model.put("amount", 150);
            return render(model, "accountForm.hbs");
        }));

        post("/user/withdrawal/150/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                    transaction = transactionAction.withDrawal(account, 150).setId(transactionService.getUniqueTransactionId()).build();

                    accountService.updateAccount(account);
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }catch(InsufficientFundsException ife){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.WITHDRAWAL.toString(), 150, false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));


        //WITHDRAWAL 200
        get("/user/withdrawal/200/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);
            model.put("amount", 200);
            return render(model, "accountForm.hbs");
        }));

        post("/user/withdrawal/200/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                    transaction = transactionAction.withDrawal(account, 200).setId(transactionService.getUniqueTransactionId()).build();

                    accountService.updateAccount(account);
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }catch(InsufficientFundsException ife){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.WITHDRAWAL.toString(), 200, false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));

        //WITHDRAWAL 300
        get("/user/withdrawal/300/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());
            model.put("amount", 300);
            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            return render(model, "accountForm.hbs");
        }));

        post("/user/withdrawal/300/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                    transaction = transactionAction.withDrawal(account, 300).setId(transactionService.getUniqueTransactionId()).build();

                    accountService.updateAccount(account);
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }catch(InsufficientFundsException ife){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.WITHDRAWAL.toString(), 300, false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));

        //WITHDRAWAL CUSTOM
        get("/user/withdrawal/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "withDrawal.hbs");
        }));

        post("/user/withdrawal/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            System.out.println(request.queryParams("amount"));
            transactionAmount = Double.valueOf(request.queryParams("amount"));

            response.redirect("/user/withdrawal/account/amount");

            return "";
        }));

        get("/user/withdrawal/account/amount", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("amount", transactionAmount);
            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            return render(model, "accountForm.hbs");
        }));


        post("/user/withdrawal/:amount/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                    transaction = transactionAction.withDrawal(account, transactionAmount.intValue()).setId(transactionService.getUniqueTransactionId()).build();

                    accountService.updateAccount(account);
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }catch(InsufficientFundsException ife){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.WITHDRAWAL.toString(), transactionAmount.intValue(), false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));

        //DEPOSIT
        get("/user/deposit/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "deposit.hbs");
        }));

        post("/user/deposit/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            System.out.println(request.queryParams("amount"));
            transactionAmount = Double.valueOf(request.queryParams("amount"));

            response.redirect("/user/deposit/account/amount");

            return "";
        }));

        get("/user/deposit/account/amount", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("amount", transactionAmount);
            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            return render(model, "accountFormDeposit.hbs");
        }));

        post("/user/deposit/:amount/account", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                double currentAccountBalance = account.getAccountBalance();

                try{
                    transaction = transactionAction.deposit(account, transactionAmount.intValue()).setId(transactionService.getUniqueTransactionId()).build();

                    accountService.updateAccount(account);
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }catch(IllegalArgumentException ila){
                    transaction = new Transaction(transactionService.getUniqueTransactionId(), TransactionType.DEPOSIT.toString(), transactionAmount.intValue(), false, LocalDateTime.now(), account.getId());
                    transactionService.insertTransaction(transaction);

                    response.redirect("/user/" + userId);
                }
            }

            return render(model, "userDashbaord.hbs");
        }));

        //BALANCE ROUTES

        get("/user/balance/account/amount", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            List<String> accountNumLIst = new ArrayList<>();

            for(Account account: accountService.selectAccount())
                accountNumLIst.add("" + account.getAccountNumber());

            model.put("amount", transactionAmount);
            model.put("accountNumList", accountNumLIst);
            model.put("userId", userId);

            return render(model, "accountFormBalance.hbs");
        }));

        post("/user/balance/account/amount", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String selectedAccount = request.queryParams("accountNum");
            Account account = accountService.selectAccount(UUID.fromString(selectedAccount));

            if(account != null){
                    transaction = transactionAction.balanceEnquiry(account).setId(transactionService.getUniqueTransactionId()).build();

                    transactionService.insertTransaction(transaction);
                }

            model.put("accountBalance", account.getAccountBalance());
            model.put("accountNumber", account.getAccountNumber());
            return render(model, "userDashbaord.hbs");
            }));




    }
}
