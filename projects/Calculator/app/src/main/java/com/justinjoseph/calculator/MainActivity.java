package com.justinjoseph.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<NumberModel> list = new ArrayList<>();
    String[] button_numbers = {
            "7","8","9",
            "4","5","6",
            "1","2","3",
            "0"
    };

    private StringBuilder numberOne = new StringBuilder("");
    private StringBuilder numberTwo = new StringBuilder("");
    private String operator;

    private TextView calc_display;
    private TextView small_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        gridView = findViewById(R.id.grid_view);

        list.add(new NumberModel("7"));
        list.add(new NumberModel("8"));
        list.add(new NumberModel("9"));
        list.add(new NumberModel("+"));
        list.add(new NumberModel("4"));
        list.add(new NumberModel("5"));
        list.add(new NumberModel("6"));
        list.add(new NumberModel("-"));
        list.add(new NumberModel("1"));
        list.add(new NumberModel("2"));
        list.add(new NumberModel("3"));
        list.add(new NumberModel("="));
        list.add(new NumberModel("0"));

        GridAdapter adapter = new GridAdapter(this, list);
        gridView.setAdapter(adapter);

//        Toast.makeText(MainActivity.this, "Haiii :3", Toast.LENGTH_LONG).show();
//        gridView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
//            String selectedItem = list.get(position).getNumber();
//            Toast.makeText(MainActivity.this, "Clicked: " + selectedItem, Toast.LENGTH_LONG).show();
//        });
        gridView.setOnItemClickListener(this::onItemClick);
        gridView.setOnItemLongClickListener(this::onItemLongClick);
        calc_display = (TextView) findViewById(R.id.calculator_display);
        small_display = (TextView) findViewById(R.id.smaller_display);

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = list.get(position).getNumber();
//        Toast.makeText(MainActivity.this, "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show();

        // Update Numbers
        for (String number : button_numbers) {
            if (selectedItem == number) {
                Log.d("ItemClick", number);
                numberOne.append(selectedItem);
                Log.d("ItemClick", numberOne.toString());
            }
        }



        // selectedItem operator functionality
        switch (selectedItem){
            case "+":
                operator = "+";
                try {
                    numberTwo = new StringBuilder(Integer.toString(Integer.parseInt(numberOne.toString())));
                } catch (NumberFormatException e){
                    Log.d("ItemClick", "number could not be converted to Integer");
                    break;
                }
                numberOne = new StringBuilder("");
                break;
            case "-":
                operator = "-";
                try {
                    numberTwo = new StringBuilder(Integer.toString(Integer.parseInt(numberOne.toString())));
                } catch (NumberFormatException e){
                    Log.d("ItemClick", "number could not be converted to Integer");
                    break;
                }
                numberOne = new StringBuilder("");
                break;

            case "=": // If the input is an equals sign, we have to make sure the numbers are populated
                int number_one;
                int number_two;
                try {
                    number_one = Integer.parseInt(numberOne.toString());
                    number_two = Integer.parseInt(numberTwo.toString());
                } catch (NumberFormatException e){
                    Log.d("ItemClick", "number could not be converted to Integer");
                    break;
                }
                if (operator == "+"){
                    numberOne = new StringBuilder(Integer.toString(number_two + number_one));
                    numberTwo =  new StringBuilder("");
                } else if (operator == "-") {
                    numberOne = new StringBuilder(Integer.toString(number_two - number_one));
                    numberTwo =  new StringBuilder("");
                } else {
                    Log.d("ItemClick", "operatoprs do not equal + or -");
                }
                break;
        }

        calc_display.setText(numberOne.toString());
        small_display.setText(numberTwo.toString());
    }
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = list.get(position).getNumber();
//        Toast.makeText(MainActivity.this, "Long Clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
        if (selectedItem == "=") {
            numberOne = new StringBuilder("");
            numberTwo = new StringBuilder("");
            operator = "";
            calc_display.setText(numberOne.toString());
            small_display.setText(numberTwo.toString());
        }
        return true;
    }
}
