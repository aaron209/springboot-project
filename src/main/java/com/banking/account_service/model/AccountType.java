package com.banking.account_service.model;

public enum AccountType {

    CHECKING("6400", 12),
    SAVING("3000", 12),
    RETIRED("5500", 10);

    private final String prefix;
    private final int length;

    AccountType(String prefix, int length) {
        this.prefix = prefix;
        this.length = length;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getLength() {
        return length;
    }
}
