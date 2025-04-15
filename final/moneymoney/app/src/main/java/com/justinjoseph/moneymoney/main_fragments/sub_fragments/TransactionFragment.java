package com.justinjoseph.moneymoney.main_fragments.sub_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.justinjoseph.moneymoney.R;
import com.justinjoseph.moneymoney.RequestHandler;
import com.justinjoseph.moneymoney.Transaction;

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
        descriptionTextView.setText(String.format("Description: %s",transaction.getDescription()));

        ImageButton deleteButton = view.findViewById(R.id.delete_transaction_button);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestHandler requestHandler = new RequestHandler(getContext(),v,getParentFragmentManager());
//                requestHandler.deleteTransaction(transaction.get_id());
//                Log.d("./TransactionFragment","Deleted transaction of id:" + transaction.get_id());
//            }
//        });

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RequestHandler requestHandler = new RequestHandler(getContext(),v,getParentFragmentManager());
                requestHandler.deleteTransaction(transaction.get_id());
                Log.d("./TransactionFragment","Deleted transaction of id:" + transaction.get_id());
                Toast.makeText(getContext(),"Deleted transaction", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}