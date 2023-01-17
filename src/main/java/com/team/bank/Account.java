package com.team.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private int id;
    private String customerName;
    private double activeBalance;
    private String address;
    private String mobile;
    private String email;
    private String bankCode;
    private String password;

    @Override
    public String toString() {

        return "\nAccount Details => accountNumber : " + id + ", customerName : " + customerName
                + ", activeBalance : " + activeBalance + ", Mobile : " + mobile + ", Email : " + email
                +", address : " + address + ", Bank Name : " + bankCode + "\n";
    }
}
