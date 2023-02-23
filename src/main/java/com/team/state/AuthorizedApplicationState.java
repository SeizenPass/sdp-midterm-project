package com.team.state;

import com.team.Application;
import com.team.bank.ATM;
import com.team.command.*;
import com.team.handler.Handler;
import com.team.handler.Request;

import java.util.Scanner;

import static com.team.Application.*;

public class AuthorizedApplicationState extends AbstractApplicationState {
    @Override
    public void render() {
        print("");
        Scanner sc = getScanner();
        ATM atm = getAtm();
        String command = sc.next();
        if (command.equals("help")) {
            print("Available commands:");
            print("- balance (check your balance)");
            print("- deposit (deposit money)");
            print("- withdraw (withdraw money)");
            print("- transfer (transfer money)");
            return;
        }
        Handler h = Application.getHandlerMap().get(command);
        ATMCommand cmd = null;
        switch (command) {
            case BALANCE_KEY:
                cmd = new CheckBalanceCommand(atm, sc);
                break;
            case DEPOSIT_KEY:
                cmd = new DepositCommand(atm, sc);
                break;
            case WITHDRAW_KEY:
                cmd = new WithdrawCommand(atm, sc);
                break;
            case TRANSFER_KEY:
                cmd = new TransferCommand(atm, sc);
                break;
        }
        if (cmd != null) {
            if (h != null) {
                h.handle(new Request(cmd));
            } else cmd.execute();
        }
    }

    public AuthorizedApplicationState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
