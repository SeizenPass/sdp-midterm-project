package com.team.command;

import com.team.bank.ATM;

import java.util.Scanner;

public class ChangeBankCommand extends ATMCommand {
    @Override
    public void execute() {
        getAtm().setCurrentService(null);
    }

    public ChangeBankCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
