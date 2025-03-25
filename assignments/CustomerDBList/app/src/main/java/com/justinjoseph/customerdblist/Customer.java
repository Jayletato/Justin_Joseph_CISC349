package com.justinjoseph.customerdblist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    protected String _id;
    protected String name;
    protected String address;
    protected String phone;
    protected String comment;

    public Customer(JSONObject jsonData) throws JSONException {
        if (jsonData.has("_id")) {
            this.setId(jsonData.getString("_id"));
        }
        this.name = (jsonData.getString("name"));
        this.address = (jsonData.getString("address"));
        this.phone = (jsonData.getString("phone"));
        this.comment = (jsonData.getString("comment"));

//      From when comments was a list of comments
//        if (jsonData.has("comments")) {
//            JSONArray cmts = jsonData.getJSONArray("comments");
//            if (null != cmts) {
//                comments = new ArrayList<String>();
//                for (int i=0 ; i < cmts.length(); i++) {
//                    comments.add(cmts.get(i).toString());
//                }
//            }
//        }
//        else {
//            comments = new ArrayList<String>();
//        }
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
}
