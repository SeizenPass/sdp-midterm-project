package com.team.command;

import com.team.bank.ATM;
import com.team.bank.DatabaseWriter;

import java.util.Scanner;

public class WithdrawCommand extends ATMCommand {
    public WithdrawCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }

    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        print("Enter amount to withdraw: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            boolean status = atm.getCurrentService().withdrawAmount(atm.getCurrentAccount(), amount);
            if (status) {
                print("Withdraw successful!");
                atm.setCurrentAccount(DatabaseWriter.getInstance().getAccountDataById(atm.getCurrentAccount().getId()));
            }
            else print("Withdraw failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
