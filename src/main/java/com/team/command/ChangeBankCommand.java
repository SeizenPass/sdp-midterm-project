package com.team.command;

import com.team.bank.ATM;
import com.team.state.DefaultApplicationState;

import java.util.Scanner;

public class ChangeBankCommand extends ATMCommand {
    @Override
    public void execute() {
        getAtm().setCurrentService(null);
        getAtm().setApplicationState(new DefaultApplicationState(getAtm(), getScanner()));
    }

    public ChangeBankCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
