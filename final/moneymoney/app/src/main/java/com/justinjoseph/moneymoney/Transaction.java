package com.justinjoseph.moneymoney;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    String _id;
    String date;
    Double amount;
    String description;
    public Transaction(JSONObject object) throws JSONException {
        this._id = object.getString("_id");
        this.date = object.getString("date");
        this.amount = object.getDouble("amount");
        this.description = object.getString("description");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getTodayString(){
        LocalDate localToday = LocalDate.now();
        String today = localToday.toString();
        return today;
    }
}
