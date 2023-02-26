package com.team.state;

import com.team.bank.ATM;
import com.team.command.blockchain.*;

import java.util.Scanner;

public class BlockchainState extends AbstractApplicationState{
    @Override
    public void render() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        String cmd = sc.next();
        switch (cmd) {
            case "create":
                new CreateWalletCommand(atm, sc).execute();
                break;
            case "balance":
                new CheckWalletBalanceCommand(atm, sc).execute();
                break;
            case "money":
                new CheckMoneyCommand(atm, sc).execute();
                break;
            case "transfer":
                new BlockchainTransferCommand(atm, sc).execute();
                break;
            case "deposit":
                new BlockchainDepositCommand(atm, sc).execute();
                break;
            case "withdraw":
                new BlockchainWithdrawCommand(atm, sc).execute();
                break;
            case "destroy":
                new DestroyWalletCommand(atm, sc).execute();
                break;
            case "owner":
                new CheckOwnerCommand(atm, sc).execute();
                break;
            case "help":
            default:
                new HelpCommand(atm, sc).execute();
        }
    }

    public BlockchainState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
