package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.blockchain.model.SimpleWallet;
import com.team.command.ATMCommand;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class CheckWalletBalanceCommand extends ATMCommand {
    @Override
    public void execute() {
        Scanner sc = getScanner();
        ATM atm = getAtm();
        print("Enter wallet address:");
        String walletAddress = sc.next();
        ContractGasProvider gasProvider =
                new StaticGasProvider(new BigInteger("20000000000"), new BigInteger("6721975"));
        SimpleWallet simpleWallet = SimpleWallet.load(walletAddress, atm.getWeb3j(), atm.getCredentials(),
               gasProvider);
        try {
            BigInteger balance = simpleWallet.getBalance().send();
            print("Your balance on " + walletAddress + ": " + balance + " Wei.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CheckWalletBalanceCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
