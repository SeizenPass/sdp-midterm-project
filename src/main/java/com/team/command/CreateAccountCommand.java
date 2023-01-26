package com.team.command;

import com.team.bank.ATM;
import com.team.bank.Account;
import com.team.bank.DatabaseWriter;

import java.util.Scanner;

public class CreateAccountCommand extends ATMCommand {
    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        Account.AccountBuilder builder = Account.builder();
        sc.nextLine();
        print("Enter name: (required)");
        while (true) {
            String name = sc.nextLine();
            if (!name.trim().isEmpty()) {
                builder.customerName(name.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter email: (required)");
        while (true) {
            String email = sc.nextLine();
            if (!email.trim().isEmpty()) {
                builder.email(email.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter password: (required)");
        while (true) {
            String password = sc.nextLine();
            if (!password.trim().isEmpty()) {
                builder.password(password.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter address:");
        String address = sc.nextLine();
        if (!address.trim().isEmpty()) {
            builder.address(address.trim());
        }
        print("Enter mobile:");
        String mobile = sc.nextLine();
        if (!mobile.trim().isEmpty()) {
            builder.mobile(mobile.trim());
        }
        builder.bankCode(atm.getCurrentService().getBankCode());
        Account account = builder.build();
        DatabaseWriter wr = DatabaseWriter.getInstance();
        wr.persistNewAccount(account);
        account = wr.login(account.getEmail(), account.getPassword(), account.getBankCode());
        if (account == null) {
            System.out.println("Something went wrong!");
        } else {
            atm.setCurrentAccount(account);
            System.out.println("You are currently logged as " + atm.getCurrentAccount().getCustomerName() + ".");
        }
    }

    public CreateAccountCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
