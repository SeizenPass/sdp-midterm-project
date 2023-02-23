package com.team.state;

import com.team.bank.ATM;
import lombok.Data;

import java.util.Scanner;

@Data
public abstract class AbstractApplicationState implements ApplicationState {
    private ATM atm;
    private Scanner scanner;

    public AbstractApplicationState(ATM atm, Scanner scanner) {
        this.atm = atm;
        this.scanner = scanner;
    }

    public void print(String s) {
        System.out.println(s);
    }
}
