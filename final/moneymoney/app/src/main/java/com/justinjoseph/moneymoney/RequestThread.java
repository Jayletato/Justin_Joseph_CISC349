package com.justinjoseph.moneymoney;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestThread extends Thread{
    RequestQueue requestQueue;
    String startDate;
    String endDate;
    String url;
    JSONObject response;
    final JSONObject[] transactions = new JSONObject[1];


    public RequestThread(RequestQueue requestQueue, String startDate, String endDate, String url) {
        this.requestQueue = requestQueue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;

    }
//    @Override
//    public void run() {
//        requestTransactions();
////        Log.d("./RequestThread", "response: " + response.toString());
//    }
//
//    // Send a POST request to get transactions from the database according to start and end date.
//    public JSONObject requestTransactions() {
//
//        try {
//            JSONObject jsonObject = new JSONObject(String.format("{\"start_date\": \"%s\", \"end_date\": \"%s\" }", startDate, endDate));
//
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url + "/get", jsonObject, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.d("./RequestThread/requestTransactions", "response: " + response.toString());
//                    //TODO: for some reason transactions[0] is always null, likely due to queue being async
//                    if (response == null) {
//                        try {
//                            response = new JSONObject("{}");
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    setResponse(response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("JSONArray Error", "Error: " + error);
//                }
//            });
//            requestQueue.add(jsonObjectRequest);
//
//            Log.d("./RequestThread", "response: " + response);
//            return response;
//        } catch (JSONException jsone) {
//            Log.d("./RequestHandler", "JSONException");
//        }
//
//        return null;
//    }
//
//    public JSONObject getResponse() {
//        return response;
//    }
//
//    public void setResponse(JSONObject response) {
//        this.response = response;
//    }
}
