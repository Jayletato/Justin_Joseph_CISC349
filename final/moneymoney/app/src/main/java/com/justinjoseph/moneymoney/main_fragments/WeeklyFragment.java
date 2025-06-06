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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeeklyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeeklyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeeklyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeeklyFragment newInstance(String param1, String param2) {
        WeeklyFragment fragment = new WeeklyFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        FragmentManager fm = getParentFragmentManager();

        TextView budgetTextView = view.findViewById(R.id.weekly_budget);
        Double budget = 0.00;

        String startDate = Transaction.getTodayString();
        String endDate = getWeekEndString();
        Log.d("./DailyFragment", "Today is: " + startDate);
        Log.d("./DailyFragment", "A week from now is: " + endDate);
        RequestHandler requestHandler = new RequestHandler(this.getContext(), view, fm);
        requestHandler.requestTransactions(startDate, endDate, budgetTextView, R.id.weekly_fragment_container);

        TextView dateRange = view.findViewById(R.id.weekly_date_range);
        dateRange.setText(String.format("%s - %s", startDate,endDate));
        return view;
    }

    public Integer getWeeklyBudget(){
        return 0;
    }
    public String getWeekEndString(){
        LocalDate localToday = LocalDate.now();
        LocalDate oneWeekFromNow = localToday.plus(6, ChronoUnit.DAYS);
        return oneWeekFromNow.toString();
    }

}