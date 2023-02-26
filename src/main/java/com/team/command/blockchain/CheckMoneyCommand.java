package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.command.ATMCommand;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CheckMoneyCommand extends ATMCommand {
    public CheckMoneyCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }

    @Override
    public void execute() {
        ATM atm = getAtm();
        Web3j web3j = atm.getWeb3j();
        String address = atm.getCredentials().getAddress();
        // Get the balance of the address
        EthGetBalance balanceResponse = null;
        try {
            balanceResponse = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BigDecimal balanceWei = new BigDecimal(balanceResponse.getBalance());

        // Convert Wei to Ether
        BigDecimal balanceEther = Convert.fromWei(balanceWei, Convert.Unit.ETHER);

        // Print the balance
        System.out.println("Balance of " + address + " is: " + balanceEther + " Ether");
    }
}
