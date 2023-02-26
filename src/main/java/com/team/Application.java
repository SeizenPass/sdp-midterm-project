package com.team;

import com.team.bank.ATM;
import com.team.blockchain.model.TestContact;
import com.team.handler.BankChosenHandler;
import com.team.handler.Handler;
import com.team.handler.UserAuthenticatedHandler;
import com.team.state.DefaultApplicationState;
import io.github.cdimascio.dotenv.Dotenv;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static final String BALANCE_KEY = "balance", DEPOSIT_KEY = "deposit",
                     WITHDRAW_KEY = "withdraw", TRANSFER_KEY = "transfer";


    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        ATM atm = new ATM();
        DatabaseCommunicator.getInstance().connect();
        print("Welcome to ATM Console.");
        Web3j web3j = Web3j.build(new HttpService(dotenv.get("BLOCKCHAIN_NODE_URI")));
        try (Scanner sc = new Scanner(System.in)) {
            atm.setApplicationState(new DefaultApplicationState(atm, sc));
            while (true) {
                atm.getApplicationState().render();
            }
        }
    }

    public static Map<String, Handler> getHandlerMap() {
        Map<String, Handler> handlerMap = new HashMap<>();
        handlerMap.put(BALANCE_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(WITHDRAW_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(DEPOSIT_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        handlerMap.put(TRANSFER_KEY, new BankChosenHandler(new UserAuthenticatedHandler(null)));
        return handlerMap;
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
