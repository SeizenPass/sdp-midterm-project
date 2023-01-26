package com.team.command;

import com.team.bank.ATM;
import lombok.Data;

import java.util.Scanner;

@Data
public abstract class ATMCommand {
    private ATM atm;
    private Scanner scanner;
    public abstract void execute();

    public ATMCommand(ATM atm, Scanner scanner) {
        this.atm = atm;
        this.scanner = scanner;
    }

    public void print(String s) {
        System.out.println(s);
    }
}
