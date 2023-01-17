package com.team.bank;

import com.team.InsufficientAmountException;
import com.team.InvalidAmountException;

public interface BankService {
    double checkBalance(Account account);

    boolean depositAmount(Account account, double depositAmount) throws InvalidAmountException;

    boolean withdrawAmount(Account account, double withdrawalAmount) throws InvalidAmountException, InsufficientAmountException;

    void transferFund(Account from, Account to, double transferAmount) throws InvalidAmountException, InsufficientAmountException ;

    void displayAccount(Account account);

    Account updateAccount(Account account, String customerName, String address, String mobile, String email);

    String getBankCode();
}
