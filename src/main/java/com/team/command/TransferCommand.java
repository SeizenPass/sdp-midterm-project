package com.team.command;

import com.team.bank.ATM;
import com.team.bank.Account;
import com.team.bank.DatabaseWriter;

import java.util.Scanner;

public class TransferCommand extends ATMCommand{
    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        DatabaseWriter wr = DatabaseWriter.getInstance();
        Account target = null;
        while (true) {
            print("Enter account email: (required)");
            String data = sc.next();
            data = data.trim();
            Account seek = wr.getAccountDataByEmail(data);
            if (seek == null) {
                print("Account not found.");
                continue;
            }
            print("Is it " + seek.getCustomerName() + "? (yes/no)");
            String r = sc.next();
            if (r.equals("yes")) {
                target = seek;
                break;
            }
        }
        print("Enter amount to transfer: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            atm.getCurrentService().transferFund(atm.getCurrentAccount(), target, amount);
            print("Transfer successful");
            atm.setCurrentAccount(wr.getAccountDataById(atm.getCurrentAccount().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TransferCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
