package com.team.bank;

import com.team.DatabaseCommunicator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseWriter {
    private static DatabaseWriter instance;

    private DatabaseWriter(){}

    public static DatabaseWriter getInstance() {
        if (instance == null) {
            instance = new DatabaseWriter();
        }
        return instance;
    }

    public void persistNewAccount(Account account) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String insertSql = "INSERT INTO accounts(customer_name, address," +
                    " mobile, email, bank_code, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(insertSql);

            stmt.setString(1, account.getCustomerName());
            stmt.setString(2, account.getAddress());
            stmt.setString(3, account.getMobile());
            stmt.setString(4, account.getEmail());
            stmt.setString(5, account.getBankCode());
            stmt.setString(6, account.getPassword());

            int row = stmt.executeUpdate();
            if (row > 0) System.out.println("Account successfully created!");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account login(String email, String password, String bankCode) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String selectSql = "SELECT id, customer_name, active_balance, address," +
                    " mobile, email, bank_code FROM accounts where email = ? AND password = ? AND bank_code = ?";
            PreparedStatement stmt = con.prepareStatement(selectSql);

            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, bankCode);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Account.builder()
                        .id(rs.getInt("id"))
                        .customerName(rs.getString("customer_name"))
                        .activeBalance(rs.getDouble("active_balance"))
                        .address(rs.getString("address"))
                        .mobile(rs.getString("mobile"))
                        .email(rs.getString("email"))
                        .bankCode(rs.getString("bank_code"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deposit(Account account, double depositAmount) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String updateSQL = "UPDATE accounts SET active_balance = active_balance + ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(updateSQL);

            stmt.setDouble(1, depositAmount);
            stmt.setInt(2, account.getId());

            int row = stmt.executeUpdate();
            if (row > 0) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean withdraw(Account account, double withdrawAmount) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String updateSQL = "UPDATE accounts SET active_balance = active_balance - ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(updateSQL);

            stmt.setDouble(1, withdrawAmount);
            stmt.setInt(2, account.getId());

            int row = stmt.executeUpdate();
            if (row > 0) return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountDataById(int id) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String selectSql = "SELECT id, customer_name, active_balance, address," +
                    " mobile, email, bank_code FROM accounts where id = ?";
            PreparedStatement stmt = con.prepareStatement(selectSql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Account.builder()
                        .id(rs.getInt("id"))
                        .customerName(rs.getString("customer_name"))
                        .activeBalance(rs.getDouble("active_balance"))
                        .address(rs.getString("address"))
                        .mobile(rs.getString("mobile"))
                        .email(rs.getString("email"))
                        .bankCode(rs.getString("bank_code"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountDataByEmail(String email) {
        Connection con = DatabaseCommunicator.getInstance().getConnection();
        try {
            String selectSql = "SELECT id, customer_name, active_balance, address," +
                    " mobile, email, bank_code FROM accounts where email = ?";
            PreparedStatement stmt = con.prepareStatement(selectSql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Account.builder()
                        .id(rs.getInt("id"))
                        .customerName(rs.getString("customer_name"))
                        .activeBalance(rs.getDouble("active_balance"))
                        .address(rs.getString("address"))
                        .mobile(rs.getString("mobile"))
                        .email(rs.getString("email"))
                        .bankCode(rs.getString("bank_code"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
