package com.team.bank;

import com.team.bank.halyk.HalykBankService;
import com.team.bank.homecredit.HomeCreditBankService;
import com.team.bank.kaspi.KaspiBankService;
import lombok.Data;

import java.util.Scanner;

@Data
public class ATM {
    private BankService currentService;
    private Account currentAccount;
    private BankService[] services = new BankService[]{new KaspiBankService(), new HalykBankService(),
            new HomeCreditBankService()};
    private String[] banks = new String[]{"Kaspi Bank", "Halyk Bank", "Home Credit Bank"};

    public void chooseBankService(Scanner sc) {
        print("Choose your bank:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < banks.length; i++) {
            sb.append(i);
            sb.append(": ");
            sb.append(banks[i]);
            print(sb.toString());
            sb.setLength(0);
        }
        boolean correctOption = false;
        int option = 0;
        while (!correctOption) {
            option = sc.nextInt();
            if (option < 0 || option >= services.length) {
                print("Incorrect option. Choose again.");
                continue;
            }
            correctOption = true;
        }
        setCurrentService(services[option]);
        print("You chose " + banks[option] + ".");
        setCurrentAccount(null);
    }

    public void createAccount(Scanner sc) {
        Account.AccountBuilder builder = Account.builder();
        sc.nextLine();
        print("Enter name: (required)");
        while (true) {
            String name = sc.nextLine();
            if (!name.trim().isEmpty()) {
                builder.customerName(name.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter email: (required)");
        while (true) {
            String email = sc.nextLine();
            if (!email.trim().isEmpty()) {
                builder.email(email.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter password: (required)");
        while (true) {
            String password = sc.nextLine();
            if (!password.trim().isEmpty()) {
                builder.password(password.trim());
                break;
            }
            print("Try again:");
        }
        print("Enter address:");
        String address = sc.nextLine();
        if (!address.trim().isEmpty()) {
            builder.address(address.trim());
        }
        print("Enter mobile:");
        String mobile = sc.nextLine();
        if (!mobile.trim().isEmpty()) {
            builder.mobile(mobile.trim());
        }
        builder.bankCode(currentService.getBankCode());
        Account account = builder.build();
        DatabaseWriter wr = DatabaseWriter.getInstance();
        wr.persistNewAccount(account);
        account = wr.login(account.getEmail(), account.getPassword(), account.getBankCode());
        if (account == null) {
            System.out.println("Something went wrong!");
        } else {
            currentAccount = account;
            System.out.println("You are currently logged as " + currentAccount.getCustomerName() + ".");
        }
    }

    public void login(Scanner sc) {
        sc.nextLine();
        print("Enter email: (required)");
        String email = "";
        while (true) {
            email = sc.nextLine();
            if (!email.trim().isEmpty()) {
                break;
            }
            print("Try again:");
        }
        print("Enter password: (required)");
        String password = "";
        while (true) {
            password = sc.nextLine();
            if (!password.trim().isEmpty()) {
                break;
            }
            print("Try again:");
        }
        DatabaseWriter wr = DatabaseWriter.getInstance();
        Account account = wr.login(email, password, currentService.getBankCode());
        if (account == null) {
            System.out.println("Login failed!");
        } else {
            currentAccount = account;
            System.out.println("Welcome back, " + currentAccount.getCustomerName() + ".");
        }
    }

    public void checkBalance() {
        print("Balance: " + currentService.checkBalance(currentAccount));
    }

    public void deposit(Scanner sc) {
        print("Enter amount to deposit: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            boolean status = currentService.depositAmount(currentAccount, amount);
            if (status) {
                print("Deposit successful!");
                currentAccount = DatabaseWriter.getInstance().getAccountDataById(currentAccount.getId());
            }
            else print("Deposit failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw(Scanner sc) {
        print("Enter amount to withdraw: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            boolean status = currentService.withdrawAmount(currentAccount, amount);
            if (status) {
                print("Withdraw successful!");
                currentAccount = DatabaseWriter.getInstance().getAccountDataById(currentAccount.getId());
            }
            else print("Withdraw failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transfer(Scanner sc) {
        DatabaseWriter wr = DatabaseWriter.getInstance();
        Account target = null;
        while (true) {
            print("Enter account email: (required)");
            String data = sc.next();
            data = data.trim();
            Account seek = wr.getAccountDataByEmail(data);
            if (seek == null) {
                print("Account not found.");
                continue;
            }
            print("Is it " + seek.getCustomerName() + "? (yes/no)");
            String r = sc.next();
            if (r.equals("yes")) {
                target = seek;
                break;
            }
        }
        print("Enter amount to transfer: (required)");
        double amount;
        amount = sc.nextDouble();
        try {
            currentService.transferFund(currentAccount, target, amount);
            print("Transfer successful");
            currentAccount = wr.getAccountDataById(currentAccount.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }
}
