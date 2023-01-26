package com.team.command;

import com.team.bank.ATM;

import java.util.Scanner;

public class CheckBalanceCommand extends ATMCommand {
    @Override
    public void execute() {
        ATM atm = getAtm();
        print("Balance: " + atm.getCurrentService().checkBalance(atm.getCurrentAccount()));
    }

    public CheckBalanceCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
