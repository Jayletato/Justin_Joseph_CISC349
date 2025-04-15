package com.justinjoseph.moneymoney.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.justinjoseph.moneymoney.R;
import com.justinjoseph.moneymoney.RequestHandler;
import com.justinjoseph.moneymoney.Transaction;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final String[] selectedDate = new String[1];
        selectedDate[0] = Transaction.getTodayString();

        TextView displayDate = view.findViewById(R.id.edit_date);
        displayDate.setText(Transaction.getTodayString());
        EditText editAmount = view.findViewById(R.id.edit_amount);
        EditText editDescription = view.findViewById(R.id.edit_description);
        Button addTransactionButton = view.findViewById(R.id.add_transaction_button);

        CalendarView calendarView = view.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Integer yearOffset = 1900; //for whatever reason Date adds 1900 to the year when making a Date
                Date thedate = new Date(year-yearOffset,month,dayOfMonth);
                Log.d("HomeFragment", String.format("%tF",thedate));
                selectedDate[0] = String.format("%tF",thedate);
                displayDate.setText(selectedDate[0]);
            }
        });

        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String date = selectedDate[0];
                    Float amount = Float.valueOf(editAmount.getText().toString());
                    String description = editDescription.getText().toString();

                    RequestHandler requestHandler = new RequestHandler(getContext(),v,getParentFragmentManager()); // RequestHandler was designed with the other main fragments in mind, so it's parameters are arbitrary here
                    requestHandler.addTransaction(date, amount,description);
                    Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT).show();
                    editAmount.setText("");
                    editDescription.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Invalid inputs", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}