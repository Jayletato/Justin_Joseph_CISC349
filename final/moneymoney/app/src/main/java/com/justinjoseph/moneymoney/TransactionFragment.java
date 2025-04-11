package com.justinjoseph.moneymoney;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TransactionFragment extends Fragment {
    Transaction transaction;

    public TransactionFragment(Transaction transaction) {
        // Required empty public constructor
        this.transaction = transaction;
    }

    // TODO: Rename and change types and number of parameters
//    public static TransactionFragment newInstance(String param1, String param2) {
//        TransactionFragment fragment = new TransactionFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        TextView dateTextView = view.findViewById(R.id.date);
        dateTextView.setText(String.format("Date: %s",transaction.getDate()));

        TextView amountTextView = view.findViewById(R.id.amount);
        amountTextView.setText(String.format("Amount: %.2f",transaction.getAmount()));

        TextView descriptionTextView = view.findViewById(R.id.description);
        descriptionTextView.setText(String.format("Amount: %s",transaction.getAmount()));

        // Inflate the layout for this fragment
        return view;
    }
}