package com.team.state;

import com.team.bank.ATM;

import java.util.Scanner;

public class ZeroState extends AbstractApplicationState {
    @Override
    public void render() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        print("Centralized or decentralized? (c/d)");
        boolean correctOption = false;
        String option = null;
        while (!correctOption) {
            option = sc.next();
            if (!option.equals("c") && !option.equals("d")) {
                print("Incorrect option. Choose again.");
                continue;
            }
            correctOption = true;
        }
        if (option.equals("c")) {
            getAtm().setApplicationState(new DefaultApplicationState(atm, sc));
        } else {
            getAtm().setApplicationState(new ZeroBlockchainState(atm, sc));
        }
    }

    public ZeroState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
