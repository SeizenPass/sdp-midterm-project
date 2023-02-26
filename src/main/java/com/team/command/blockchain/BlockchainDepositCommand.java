package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.blockchain.model.SimpleWallet;
import com.team.command.ATMCommand;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Scanner;

public class BlockchainDepositCommand extends ATMCommand {
    public BlockchainDepositCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }

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
        print("Enter amount:");
        BigInteger amount = new BigInteger(sc.next());
        try {
            simpleWallet.deposit(amount).send();
            print("Funds deposited.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
