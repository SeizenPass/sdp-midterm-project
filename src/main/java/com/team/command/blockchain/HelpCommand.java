package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.command.ATMCommand;

import java.util.Scanner;

public class HelpCommand extends ATMCommand {
    @Override
    public void execute() {
        print("Available Commands: ");
        print("- help (this menu)");
        print("- create (create wallet)");
        print("- money (view your own balance)");
        print("- balance (view balance on specific wallet)");
        print("- deposit (deposit balance to specific wallet)");
        print("- withdraw (deposit balance from specific wallet)");
        print("- transfer (transfer balance on specific wallet)");
        print("- destroy (destroy specific wallet)");
        print("- owner (check owner of specific wallet)");
    }

    public HelpCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
