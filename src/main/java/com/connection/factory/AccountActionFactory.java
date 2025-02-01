package com.connection.factory;

public class AccountActionFactory {
    private static AccountAction accountAction = null;

    static {
        accountAction = new AccountAction();
    }

    public static AccountAction getAccountAction() {
        return accountAction;
    }
}

