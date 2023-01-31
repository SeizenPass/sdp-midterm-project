package com.team.handler;

import com.team.bank.ATM;
import com.team.command.ATMCommand;
import lombok.Data;

@Data
public class Request {
    private ATMCommand command;

    public Request(ATMCommand command) {
        this.command = command;
    }
}
