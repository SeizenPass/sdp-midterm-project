package com.team;

import com.team.bank.ATM;
import com.team.command.*;
import com.team.handler.BankChosenHandler;
import com.team.handler.Handler;
import com.team.handler.Request;
import com.team.handler.UserAuthenticatedHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static final String BALANCE_KEY = "balance", DEPOSIT_KEY = "deposit",
                     WITHDRAW_KEY = "withdraw", TRANSFER_KEY = "transfer";


    public static void main(String[] args) {
        ATM atm = new ATM();
        DatabaseCommunicator.getInstance().connect();
        Map<String, Handler> handlerMap = getHandlerMap();
        print("Welcome to ATM Console.");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                if (atm.getCurrentService() == null) {
                    new ChooseBankCommand(atm, sc).execute();
                    continue;
                }
                if (atm.getCurrentAccount() == null) {
                    print("Would you like to login, create new account or change bank? (login/create/change)");
                    String command;
                    do {
                        command = sc.next();
                    } while (!command.equals("login") && !command.equals("create") && !command.equals("change"));
                    if (command.equals("login")) {
                        new LoginCommand(atm, sc).execute();
                    } else if (command.equals("create")) {
                        new CreateAccountCommand(atm, sc).execute();
                    } else {
                        new ChangeBankCommand(atm, sc).execute();
                    }
                    continue;
                }
                print("");
                String command = sc.next();
                if (command.equals("help")) {
                    print("Available commands:");
                    print("- balance (check your balance)");
                    print("- deposit (deposit money)");
                    print("- withdraw (withdraw money)");
                    print("- transfer (transfer money)");
                    continue;
                }
                Handler h = handlerMap.get(command);
                ATMCommand cmd = null;
                switch (command) {
                    case BALANCE_KEY:
                        cmd = new CheckBalanceCommand(atm, sc);
                        break;
                    case DEPOSIT_KEY:
                        cmd = new DepositCommand(atm, sc);
                        break;
                    case WITHDRAW_KEY:
                        cmd = new WithdrawCommand(atm, sc);
                        break;
                    case TRANSFER_KEY:
                        cmd = new TransferCommand(atm, sc);
                        break;
                }
                if (cmd != null) {
                    if (h != null) {
                        h.handle(new Request(cmd));
                    } else cmd.execute();
                }
            }
        }
    }

    public static Map<String, Handler> getHandlerMap() {
        Map<String, Handler> handlerMap = new HashMap<>();
        handlerMap.put(BALANCE_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(WITHDRAW_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(DEPOSIT_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(TRANSFER_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        return handlerMap;
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
