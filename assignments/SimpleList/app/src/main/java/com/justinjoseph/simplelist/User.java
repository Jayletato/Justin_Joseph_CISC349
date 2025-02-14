package com.justinjoseph.simplelist;

public class User {
    private final String name;
    private final String phone;
    private final String description;

    public User(String name, String phone, String description) {
        this.name = name;
        this.phone = phone;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {return this.phone;}
    public String getDescription(){return this.description;}
}
