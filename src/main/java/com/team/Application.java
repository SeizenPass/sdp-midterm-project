package com.team;

import com.team.bank.ATM;
import com.team.command.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM();
        DatabaseCommunicator.getInstance().connect();
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
                if (command.equals("balance")) {
                    new CheckBalanceCommand(atm, sc).execute();
                    continue;
                }
                if (command.equals("deposit")) {
                    new DepositCommand(atm, sc).execute();
                    continue;
                }
                if (command.equals("withdraw")) {
                    new WithdrawCommand(atm, sc).execute();
                    continue;
                }
                if (command.equals("transfer")) {
                    new TransferCommand(atm, sc).execute();
                    continue;
                }
            }
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
