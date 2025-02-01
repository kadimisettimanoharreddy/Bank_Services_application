package com.connection.factory;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.codegnan.beans.Account;

public class AccountAction {
    public String addAccount(Account account) {
        String status = "";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pr = connection.prepareStatement("INSERT INTO Bankaccount VALUES (?, ?, ?, ?, ?, ?,?)");
            pr.setString(1, account.getAccNo());
            pr.setString(2, account.getAccHolderName());
            pr.setString(3, account.getAccType());
            pr.setFloat(4, account.getBalance());
            pr.setString(5, account.getAccBranch());
            pr.setString(6, account.getAccBank());
            pr.setString(7, account.getEmail());
            int rowCount = pr.executeUpdate();
            status = (rowCount == 1) ? "success" : "failed";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
