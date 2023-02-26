package com.team.bank;

import com.team.Application;
import com.team.bank.halyk.HalykBankService;
import com.team.bank.homecredit.HomeCreditBankService;
import com.team.bank.kaspi.KaspiBankService;
import com.team.state.ApplicationState;
import lombok.Data;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

@Data
public class ATM {
    private Web3j web3j;
    private Credentials credentials;
    private BankService currentService;
    private Account currentAccount;
    private ApplicationState applicationState;
    private BankService[] services = new BankService[]{new KaspiBankService(), new HalykBankService(),
            new HomeCreditBankService()};
    private String[] banks = new String[]{"Kaspi Bank", "Halyk Bank", "Home Credit Bank"};

    public static void print(String s) {
        System.out.println(s);
    }
}
