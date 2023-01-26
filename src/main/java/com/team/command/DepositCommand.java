package com.team.command;

import com.team.bank.ATM;
import com.team.bank.DatabaseWriter;

import java.util.Scanner;

public class DepositCommand extends ATMCommand{
    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        print("Enter amount to deposit: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            boolean status = atm.getCurrentService().depositAmount(atm.getCurrentAccount(), amount);
            if (status) {
                print("Deposit successful!");
                atm.setCurrentAccount(DatabaseWriter.getInstance()
                        .getAccountDataById(atm.getCurrentAccount().getId()));
            }
            else print("Deposit failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DepositCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
