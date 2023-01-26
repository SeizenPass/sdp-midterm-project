package com.team.command;

import com.team.bank.ATM;
import com.team.bank.Account;
import com.team.bank.DatabaseWriter;

import java.util.Scanner;

public class LoginCommand extends ATMCommand {
    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        sc.nextLine();
        print("Enter email: (required)");
        String email = "";
        while (true) {
            email = sc.nextLine();
            if (!email.trim().isEmpty()) {
                break;
            }
            print("Try again:");
        }
        print("Enter password: (required)");
        String password = "";
        while (true) {
            password = sc.nextLine();
            if (!password.trim().isEmpty()) {
                break;
            }
            print("Try again:");
        }
        DatabaseWriter wr = DatabaseWriter.getInstance();
        Account account = wr.login(email, password, atm.getCurrentService().getBankCode());
        if (account == null) {
            System.out.println("Login failed!");
        } else {
            atm.setCurrentAccount(account);
            System.out.println("Welcome back, " + atm.getCurrentAccount().getCustomerName() + ".");
        }
    }

    public LoginCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
