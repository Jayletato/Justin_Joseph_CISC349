package com.justinjoseph.moneymoney.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justinjoseph.moneymoney.R;
import com.justinjoseph.moneymoney.Transaction;
import com.justinjoseph.moneymoney.TransactionFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyFragment newInstance(String param1, String param2) {
        DailyFragment fragment = new DailyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        FragmentManager fm = getParentFragmentManager();

        TextView budgetTextView = view.findViewById(R.id.daily_budget);
        Double budget = 0.00;
        try {
            JSONArray response = new JSONArray();
            response.put(new JSONObject("{\"_id\": \"67f815cff9999f6dcf8cfb87\", \"date\": \"2025-04-10\", \"amount\": -5.0, \"description\": \"rent payment for living in\n" + "the walls\"}"));
            response.put(new JSONObject("{\"_id\": \"67f89954f9999f6dcf8cfb8a\", \"date\": \"2025-04-10\", \"amount\": 20.0, \"description\": \"20 bucks is 20\n" + "bucks\"}"));

            for (int i = 0; i < response.length(); i++){
                JSONObject tJsonObject = response.getJSONObject(i);
                Transaction transaction = new Transaction(tJsonObject);

                // Add fragment for each money transaction
                Fragment tFragment = new TransactionFragment(transaction);
                fm.beginTransaction().add(R.id.daily_fragment_container, tFragment).commit();

                // Update budget
                budget += transaction.getAmount();

            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }

        budgetTextView.setText(budget.toString());
        return view;
    }

    public Integer getDailyBudget(){
        return 0;
    }
}