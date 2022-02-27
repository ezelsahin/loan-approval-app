package com.paycore.loanapproval.entity;


public enum RequestStatus {
    APPROVED ("APPROVED"),
    DENIED ("DENIED");

    private final String name;

    private RequestStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
