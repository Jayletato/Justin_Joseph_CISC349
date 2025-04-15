package com.justinjoseph.moneymoney.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justinjoseph.moneymoney.R;
import com.justinjoseph.moneymoney.RequestHandler;
import com.justinjoseph.moneymoney.Transaction;


public class DailyFragment extends Fragment {


    public DailyFragment() {
        // Required empty public constructor
    }


    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        FragmentManager fm = getParentFragmentManager();

        TextView budgetTextView = view.findViewById(R.id.daily_budget);
        Double budget = 0.00;

        String start_date = Transaction.getTodayString();
        String end_date = start_date;
        Log.d("./DailyFragment", "Today is: " + Transaction.getTodayString());
        RequestHandler requestHandler = new RequestHandler(this.getContext(), view, fm);
        requestHandler.requestTransactions(start_date, end_date, budgetTextView, R.id.daily_fragment_container);
//        try {
//            RequestHandler requestHandler = new RequestHandler(this.getContext());
//            JSONObject transactionsObject = requestHandler.requestTransactions("2025-04-10", "2025-04-10");
////            Log.d("./DailyFragment", transactionsObject.toString());
//            JSONArray transactionsArray = transactionsObject.getJSONArray("transactions");
//            JSONArray response = new JSONArray();
//            response.put(new JSONObject("{\"_id\": \"67f815cff9999f6dcf8cfb87\", \"date\": \"2025-04-10\", \"amount\": -5.0, \"description\": \"rent payment for living in\n" + "the walls\"}"));
//            response.put(new JSONObject("{\"_id\": \"67f89954f9999f6dcf8cfb8a\", \"date\": \"2025-04-10\", \"amount\": 20.0, \"description\": \"20 bucks is 20\n" + "bucks\"}"));
//
//            for (int i = 0; i < response.length(); i++){
//                JSONObject tJsonObject = response.getJSONObject(i);
//                Transaction transaction = new Transaction(tJsonObject);
//
//                // Add fragment for each money transaction
//                Fragment tFragment = new TransactionFragment(transaction);
//                fm.beginTransaction().add(R.id.daily_fragment_container, tFragment).commit();
//
//                // Update budget
//                budget += transaction.getAmount();
//
//
//            }
//        } catch (JSONException jsone) {
//            jsone.printStackTrace();
//        }
//
//        budgetTextView.setText(budget.toString());
        return view;
    }

    public Integer getDailyBudget(){
        return 0;
    }

//    public String getTodayString(){
//        LocalDate localToday = LocalDate.now();
//        String today = localToday.toString();
//        return today;
//    }
}