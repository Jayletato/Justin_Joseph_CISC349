package com.justinjoseph.moneymoney;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.justinjoseph.moneymoney.main_fragments.sub_fragments.TransactionFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandler {
    RequestQueue queue;
    Context context;
    String url;

    View view;
    FragmentManager fm;

    public RequestHandler(Context context, View view, FragmentManager fm){
        // Use the application context
        this.context = context;
        this.view = view;
        this.fm = fm;
        url = context.getString(R.string.url);

        //Start a RequestQueue upon initialization
        queue = Volley.newRequestQueue(context);
    }

    // Send a POST request to get transactions from the database according to start and end date.
    public void requestTransactions(String startDate, String endDate, TextView budgetTextView, Integer fragmentContainerID) { // This function should be redesigned with view population done separately if possible
//        RequestThread requestThread = new RequestThread(queue, startDate,endDate,url);
//        requestThread.run();
//        return requestThread.getResponse();

        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"start_date\": \"%s\", \"end_date\": \"%s\" }", startDate, endDate));
            final JSONObject[] transactions = new JSONObject[1];

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url + "/get", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("./RequestHandler/requestTransactions", response.toString());
                    //TODO: for some reason transactions[0] is always null
                    transactions[0] = response;
                    Log.d("./RequestHandler/requestTransactions", transactions[0].toString());
                    viewsPopulater(transactions[0], budgetTextView, fragmentContainerID);
//                    viewsPopulater(view, budgetTextView, fragmentContainerID, transactions[0], fm); // If viewsPopulater is adapted so that one RequestHandler can handle multiple fragments

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSONArray Error", "Error: " + error);
                }
            });
            queue.add(jsonObjectRequest);

//            return transactions[0];
        } catch (JSONException jsone) {
            Log.d("./RequestHandler", "JSONException");
        }

//        return null;
    }

    // For one RequestHandler to handle every view instead of one RequestHandler to handle one view:
    //     public void viewsPopulater(View view, TextView budgetTextView, Integer fragmentContainerID, JSONObject transactionsObject, FragmentManager fm)
    public void viewsPopulater(JSONObject transactionsObject, TextView budgetTextView, Integer fragmentContainerID){
        Double budget = 0.00;
        try {
//            Log.d("./DailyFragment", transactionsObject.toString());
            JSONArray transactionsArray = transactionsObject.getJSONArray("transactions");
            JSONArray response = transactionsArray;
//            response.put(new JSONObject("{\"_id\": \"67f815cff9999f6dcf8cfb87\", \"date\": \"2025-04-10\", \"amount\": -5.0, \"description\": \"rent payment for living in\n" + "the walls\"}"));
//            response.put(new JSONObject("{\"_id\": \"67f89954f9999f6dcf8cfb8a\", \"date\": \"2025-04-10\", \"amount\": 20.0, \"description\": \"20 bucks is 20\n" + "bucks\"}"));

            for (int i = 0; i < response.length(); i++){
                JSONObject tJsonObject = response.getJSONObject(i);
                Transaction transaction = new Transaction(tJsonObject);

                // Add fragment for each money transaction
                Fragment tFragment = new TransactionFragment(transaction);
                fm.beginTransaction().add(fragmentContainerID, tFragment).commit();

                // Update budget
                budget += transaction.getAmount();

            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }

        budgetTextView.setText(budget.toString());
    }

    public void addTransaction(String date, Float amount, String description) {
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"date\": \"%s\", \"amount\": \"%.2f\", \"description\": \"%s\"}", date, amount, description));


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url + "/add", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("./RequestHandler/addTransaction", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSONArray Error", "Error: " + error);
                }
            });
            queue.add(jsonObjectRequest);

//            return transactions[0];
        } catch (JSONException jsone) {
            Log.d("./RequestHandler", "JSONException");
        }
    }
    public void deleteTransaction(String id) {
        try {
            JSONObject jsonObject = new JSONObject(String.format("{\"_id\": \"%s\"}", id));


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url + "/del", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("./RequestHandler/delTransaction", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSONArray Error", "Error: " + error);
                }
            });
            queue.add(jsonObjectRequest);

//            return transactions[0];
        } catch (JSONException jsone) {
            Log.d("./RequestHandler", "JSONException");
        }
    }
}
