package com.codegnan.beans;

public class Account {
    private String accNo;
    private String accHolderName;
    private String accType;
    private float balance;
    private String accBranch;
    private String accBank;
    private String email; // Changed phoneNum to email

    public Account() {
        super();
    }

    public Account(String accNo, String accHolderName, String accType, float balance, String accBranch, String accBank, String email) {
        super();
        this.accNo = accNo;
        this.accHolderName = accHolderName;
        this.accType = accType;
        this.balance = balance;
        this.accBranch = accBranch;
        this.accBank = accBank;
        this.email = email; // Updated parameter
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccBranch() {
        return accBranch;
    }

    public void setAccBranch(String accBranch) {
        this.accBranch = accBranch;
    }

    public String getAccBank() {
        return accBank;
    }

    public void setAccBank(String accBank) {
        this.accBank = accBank;
    }

    public String getEmail() { // Added email getter
        return email;
    }

    public void setEmail(String email) { // Added email setter
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account [accNo=" + accNo + ", accHolderName=" + accHolderName + ", accType=" + accType + ", balance="
                + balance + ", accBranch=" + accBranch + ", accBank=" + accBank + ", email=" + email + "]"; // Updated toString
    }
}

