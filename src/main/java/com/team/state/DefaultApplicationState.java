package com.team.state;

import com.team.bank.ATM;
import com.team.command.ChooseBankCommand;

import java.util.Scanner;

public class DefaultApplicationState extends AbstractApplicationState {
    @Override
    public void render() {
        new ChooseBankCommand(getAtm(), getScanner()).execute();
    }

    public DefaultApplicationState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
