package com.blan8k.unitify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Temperature extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temperature, container, false);

        ListView M1;
        M1 = (ListView) view.findViewById(R.id.M1);
        ArrayList<String> mArrayList = new ArrayList<>();
        mArrayList.add("Fahrenheit");
        mArrayList.add("Celsius");
        mArrayList.add("Kelvin");

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                mArrayList);
        M1.setAdapter(arrayAdapter1);



        ListView M2;
        M2 = (ListView) view.findViewById(R.id.M2);
        M2.setAdapter(arrayAdapter1);



        M1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l) {
                TextView unit1 = view.findViewById(R.id.Unit1);
                String unit = mArrayList.get(i);
                EditText input = view.findViewById(R.id.input);
                if(input.getText().toString().equals("") || input.getText().toString().equals(".")){
                    Toast.makeText(getActivity(), "Enter a valid number first.", Toast.LENGTH_SHORT).show();
                    return;
                }

                unit1.setText(unit);
            }
        });

        M2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l) {
                TextView unit1 = view.findViewById(R.id.Unit1);
                EditText input = view.findViewById(R.id.input);
                if(unit1.getText().toString().equals("") || input.getText().toString().equals(".")
                        || input.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Fill in the initial values first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                TextView unit2 = view.findViewById(R.id.Unit2);
                String unit = mArrayList.get(i);
                //Calculate Answer
                double number = Double.parseDouble(input.getText().toString());
                TextView answer = view.findViewById(R.id.Answer);
                double result = 0;
                switch (unit1.getText().toString()){
                    case "Fahrenheit":
                        if(i == 0){
                            result = number;
                        }else if(i == 1){
                            result = (number - 32) * 5/9;
                        }else if(i == 2){
                            result = (number - 32) * 5/9 + 273.15;
                        }

                        break;
                    case "Celsius":
                        if(i == 0){
                            result = number * 9/5 + 32;
                        }else if(i == 1){
                            result = number;
                        }else if(i == 2){
                            result = number + 273.15;
                        }
                        break;
                    case "Kelvin":
                        if(i == 0){
                            result = (number - 273.15) * 9/5 + 32;
                        }else if(i == 1){
                            result = number - 273.15;
                        }else if(i == 2){
                            result = number;
                        }
                        break;
                }


                BigDecimal a = new BigDecimal(String.valueOf(result));
                BigDecimal b = a.setScale(7, RoundingMode.DOWN); // => BigDecimal("1.23")
                //DecimalFormat format = new DecimalFormat("0.#");

                String num = String.valueOf(b.stripTrailingZeros());
//                int endIndex = num.length();
//                for(int j = 0; j < 10; j++){
//                    if(num.charAt(endIndex - 1 - j ) != '0'){
//                        endIndex = endIndex - j;
//                    }
//                }
                answer.setText(num);

                unit2.setText(unit);
            }
        });

        return view;
    }
}
