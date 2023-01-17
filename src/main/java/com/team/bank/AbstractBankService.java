package com.team.bank;

import com.team.InsufficientAmountException;
import com.team.InvalidAmountException;

public abstract class AbstractBankService implements BankService {
    private String invalidErrorMessage  = "Enter Valid Amount";
    private String insufficientErrorMessage = "Insufficient Fund";

    @Override
    public double checkBalance(Account account) {
        return account.getActiveBalance();
    }

    @Override
    public boolean depositAmount(Account account, double depositAmount) throws InvalidAmountException {

        if(depositAmount <= 0) {
            throw new InvalidAmountException(invalidErrorMessage);
        }
        else {
            DatabaseWriter wr = DatabaseWriter.getInstance();
            wr.deposit(account, depositAmount);
            return true;
        }
    }

    @Override
    public boolean withdrawAmount(Account account, double withdrawalAmount) throws InvalidAmountException, InsufficientAmountException {

        if(withdrawalAmount <= 0) {
            throw new InvalidAmountException(invalidErrorMessage);
        }
        else {
            if(account.getActiveBalance() > withdrawalAmount) {

                DatabaseWriter wr = DatabaseWriter.getInstance();
                wr.withdraw(account, withdrawalAmount);
                return true;
            }
            else
                throw new InsufficientAmountException(insufficientErrorMessage);
        }
    }

    @Override
    public void transferFund(Account from, Account to, double transferAmount) throws InvalidAmountException, InsufficientAmountException  {

        if(transferAmount <= 0)
            throw new InvalidAmountException(invalidErrorMessage);
        else if(from.getActiveBalance() < transferAmount)
            throw new InsufficientAmountException(insufficientErrorMessage);
        else {
            DatabaseWriter wr = DatabaseWriter.getInstance();
            wr.withdraw(from, transferAmount);
            wr.deposit(to, transferAmount);
        }
    }

    @Override
    public void displayAccount(Account account) {
        System.out.println(account);
    }

    @Override
    public Account updateAccount(Account account, String customerName, String address, String mobile, String email) {

        account.setCustomerName(customerName);
        account.setAddress(address);
        account.setMobile(mobile);
        account.setEmail(email);
        System.out.println("\nAccount Details Updated Successfully in your Kaspi Bank Account!!!\n");
        return account;
    }
}
