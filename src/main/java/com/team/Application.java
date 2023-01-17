package com.team;

import com.team.bank.ATM;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ATM atm = new ATM();
        DatabaseCommunicator.getInstance().connect();
        print("Welcome to ATM Console.");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                if (atm.getCurrentService() == null) {
                    atm.chooseBankService(sc);
                    continue;
                }
                if (atm.getCurrentAccount() == null) {
                    print("Would you like to login, create new account or change bank? (login/create/change)");
                    String command;
                    do {
                        command = sc.next();
                    } while (!command.equals("login") && !command.equals("create") && !command.equals("change"));
                    if (command.equals("login")) {
                        atm.login(sc);
                    } else if (command.equals("create")) {
                        atm.createAccount(sc);
                    } else {
                        atm.setCurrentService(null);
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
                    atm.checkBalance();
                    continue;
                }
                if (command.equals("deposit")) {
                    atm.deposit(sc);
                    continue;
                }
                if (command.equals("withdraw")) {
                    atm.withdraw(sc);
                    continue;
                }
                if (command.equals("transfer")) {
                    atm.transfer(sc);
                    continue;
                }
            }
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
