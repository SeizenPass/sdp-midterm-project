package com.team;

import com.team.bank.Account;
import com.team.bank.BankService;
import com.team.bank.halyk.HalykBankService;
import com.team.bank.homecredit.HomeCreditBankService;
import com.team.bank.kaspi.KaspiBankService;

import java.util.Scanner;

public class OldApp {
//    public static void main(String[] args) {
//
//        try (Scanner scanner = new Scanner(System.in)) {
//
//            int defaultAccountNumber = 101;
//            BankService serviceObject = new KaspiBankService();
//            System.out.println("Welcome to ATM Console.");
//
//            System.out.println("\nChoose Your Bank to Create Account : \n1) State Bank Of India \n2) Indian Bank \n3) City Union Bank");
//            int bankOption = scanner.nextInt();
//
//            System.out.println("\nPlease enter your details as Name, initial Balance, Address, mobile, email to create a new Account...");
//            scanner.nextLine();
//            String[] customerDetails = scanner.nextLine().split(",");
//            Account account1 = null;
//
////            if(bankOption == 1)
////                account1 = new KaspiBankAccount(defaultAccountNumber++ ,customerDetails[0], Double.parseDouble(customerDetails[1]),
////                        customerDetails[2], Long.parseLong(customerDetails[3]), customerDetails[4]);
////            else if(bankOption == 2)
////                account1 = new HalykBankAccount(defaultAccountNumber++ ,customerDetails[0], Double.parseDouble(customerDetails[1]),
////                        customerDetails[2], Long.parseLong(customerDetails[3]), customerDetails[4]);
////            else
////                account1 = new HomeCreditBankAccount(defaultAccountNumber++ ,customerDetails[0], Double.parseDouble(customerDetails[1]),
////                        customerDetails[2], Long.parseLong(customerDetails[3]), customerDetails[4]);
//
//            System.out.println(account1);
//
//            Account account2 =
////                    new Account(defaultAccountNumber++, "Raj", 2000.00, "xyz", "1234512345", "raj@gmail.com", "State Bank");
//new Account.builder();
//            System.out.println("Choose Your ATM to Perform Operation : \n1) State Bank Of India \n2) Indian Bank \n3) City Union Bank");
//            int atmOption = scanner.nextInt();
//            if(atmOption == 1) {
//                System.out.println("\n**** Operating at State Bank ATM ****\n");
//            }
//            else if(atmOption == 2) {
//                System.out.println("\n**** Operating at Indian Bank ATM ****\n");
//                serviceObject = new HalykBankService();
//            }
//            else if(atmOption == 3) {
//                System.out.println("\n**** Operating at City Bank ATM ****\n");
//                serviceObject = new HomeCreditBankService();
//            }
//
//            while(true) {
//
//                System.out.println("choose an option : \n1) Deposit \n2) Withdraw \n3) Check Balance "
//                        + "\n4) Transfer Fund \n5) Display All \n6) Update Account \n7) Exit");
//                int option = scanner.nextInt();
//                switch(option) {
//
//                    case 1: {
//
//                        System.out.println("\n<=> Deposit Menu <=>\n");
//                        System.out.println("Enter Depositing Amount : ");
//                        double depositingAmount = scanner.nextDouble();
//
//                        try {
//                            if(serviceObject.depositAmount(account1, depositingAmount))
//                                System.out.println("\nRs: " + depositingAmount + " INR successfully deposited to the account No "
//                                        + account1.getAccountNumber() + "\n");
//                        }
//                        catch (InvalidAmountException exception) {
//                            System.err.println(exception.getMessage());
//                        }
//                        break;
//                    }
//                    case 2: {
//
//                        System.out.println("\n<=> Withdraw Menu <=>\n");
//                        System.out.println("Enter Withdrawal Amount : ");
//                        double withdrawalAmount = scanner.nextDouble();
//
//                        try {
//                            if(serviceObject.withdrawAmount(account1, withdrawalAmount))
//                                System.out.println("\nAmount Rs: " + withdrawalAmount + " INR successfully withdrawn from the account No "
//                                        + account1.getAccountNumber() + "\n");
//                        }
//                        catch (InvalidAmountException | InsufficientAmountException exception) {
//                            System.err.println(exception.getMessage());
//                        }
//                        break;
//                    }
//                    case 3: {
//
//                        System.out.println("\n<=> Check Balance Menu <=>\n");
//                        System.out.println("Current Account Balance is Rs: " +  serviceObject.checkBalance(account1) + "INR\n");
//                        break;
//                    }
//                    case 4: {
//
//                        System.out.println("\n<=> Transfer Funds Menu <=>\n");
//                        System.out.println("Enter Trasfering amount : ");
//                        double transferAmount = scanner.nextDouble();
//
//                        try {
//                            serviceObject.transferFund(account1, account2, transferAmount);
//                        }
//                        catch (InvalidAmountException | InsufficientAmountException exception) {
//                            System.err.println(exception.getMessage());
//                        }
//                        break;
//                    }
//                    case 5: {
//
//                        System.out.println("<=> Display All Accounts Menu <=>");
//                        serviceObject.displayAccount(account1);
//                        break;
//                    }
//                    case 6: {
//
//                        System.out.println("\n<=> Update Accounts Menu <=>\n");
//                        updateAccount(scanner, serviceObject, account1);
//                        break;
//                    }
//                    case 7: {
//
//                        System.out.println("\nThankyou !!! Have a good day :)\n");
//                        return;
//                    }
//                    default: {
//
//                        System.out.println("\nChoose a valid Option !!!\n");
//                    }
//                }
//            }
//        }
//        catch (Exception exception) {
//            System.err.println(exception);
//        }
//    }
//
//    public static void updateAccount(Scanner scanner, BankService accountService, Account account) {
//
//        System.out.println("Enter Details to be Updated as name, address, mobile, email : ");
//        scanner.nextLine();
//        String[] updatingDetails = scanner.nextLine().split(",");
//        System.out.println(accountService.updateAccount(account, updatingDetails[0], updatingDetails[1],
//                updatingDetails[2], updatingDetails[3]));
//    }
}
