package com.team.command.blockchain;

import com.team.bank.ATM;
import com.team.blockchain.EthereumService;
import com.team.blockchain.model.SimpleWallet;
import com.team.command.ATMCommand;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CreateWalletCommand extends ATMCommand {
    @Override
    public void execute() {
        ATM atm = getAtm();
        Web3j web3j = atm.getWeb3j();
        ContractGasProvider gasProvider =
                new StaticGasProvider(new BigInteger("20000000000"), new BigInteger("6721975"));
        try {
            SimpleWallet simpleWallet = SimpleWallet.deploy(web3j, atm.getCredentials(), gasProvider).send();
            String contractAddress = simpleWallet.getContractAddress();
            // Print the deployed contract's address
            System.out.println("SimpleWallet contract deployed to address: " + contractAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public CreateWalletCommand(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
