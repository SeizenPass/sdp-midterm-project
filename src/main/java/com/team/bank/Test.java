package com.team.bank;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        System.out.println("{" + data + "}");
        System.out.println(data.isEmpty());
    }
}
