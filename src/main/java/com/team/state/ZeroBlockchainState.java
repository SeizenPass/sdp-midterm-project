package com.team.state;

import com.team.bank.ATM;
import org.web3j.crypto.Credentials;
import java.util.Scanner;

public class ZeroBlockchainState extends AbstractApplicationState {

    @Override
    public void render() {
        boolean f = true;
        while (f) {
            Scanner sc = getScanner();
            print("Enter your private key:");
            String privateKey = sc.next();
            Credentials credentials = Credentials.create(privateKey);
            getAtm().setCredentials(credentials);
            f = false;
        }
        getAtm().setApplicationState(new BlockchainState(getAtm(), getScanner()));
        print("Proceed to work.");
    }

    public ZeroBlockchainState(ATM atm, Scanner scanner) {
        super(atm, scanner);
    }
}
