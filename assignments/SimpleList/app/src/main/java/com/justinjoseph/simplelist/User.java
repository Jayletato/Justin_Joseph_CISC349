package com.justinjoseph.simplelist;

public class User {
    private final String name;
    private final String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }
}
