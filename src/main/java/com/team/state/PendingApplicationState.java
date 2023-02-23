package com.team.state;

import com.team.bank.ATM;
import com.team.command.ChangeBankCommand;
import com.team.command.CreateAccountCommand;
import com.team.command.LoginCommand;

import java.util.Scanner;

public class PendingApplicationState extends AbstractApplicationState {
    @Override
    public void render() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
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
    }

    public PendingApplicationState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
