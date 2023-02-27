package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.blockchain.model.SimpleWallet;
import com.team.command.ATMCommand;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Scanner;

public class DestroyWalletCommand extends ATMCommand {
    public DestroyWalletCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }

    @Override
    public void execute() {
        ATM atm = getAtm();
        Scanner sc = getScanner();
        print("Enter wallet address:");
        String walletAddress = sc.next();
        ContractGasProvider gasProvider =
                new StaticGasProvider(new BigInteger("20000000000"), new BigInteger("6721975"));
        try {
            SimpleWallet simpleWallet = SimpleWallet.load(walletAddress, atm.getWeb3j(), atm.getCredentials(),
                    gasProvider);
            simpleWallet.destroy().send();
            System.out.println("Wallet was destroyed and funds were sent to owner of the wallet.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
