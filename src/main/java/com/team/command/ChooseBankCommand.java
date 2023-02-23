package com.team.command;

import com.team.bank.ATM;
import com.team.state.PendingApplicationState;

import java.util.Scanner;

public class ChooseBankCommand extends ATMCommand {

    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        print("Choose your bank:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < atm.getBanks().length; i++) {
            sb.append(i);
            sb.append(": ");
            sb.append(atm.getBanks()[i]);
            print(sb.toString());
            sb.setLength(0);
        }
        boolean correctOption = false;
        int option = 0;
        while (!correctOption) {
            option = sc.nextInt();
            if (option < 0 || option >= atm.getServices().length) {
                print("Incorrect option. Choose again.");
                continue;
            }
            correctOption = true;
        }
        atm.setCurrentService(atm.getServices()[option]);
        print("You chose " + atm.getBanks()[option] + ".");
        atm.setCurrentAccount(null);
        atm.setApplicationState(new PendingApplicationState(atm, sc));
    }

    public ChooseBankCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
