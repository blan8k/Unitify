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

public class Mass extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mass, container, false);

        ListView M1;
        M1 = (ListView) view.findViewById(R.id.M1);
        ArrayList<String> mArrayList = new ArrayList<>();
        mArrayList.add("Milligram");
        mArrayList.add("Centigram");
        mArrayList.add("Decigram");
        mArrayList.add("Gram");
        mArrayList.add("Decagram");
        mArrayList.add("Hectogram");
        mArrayList.add("Kilogram");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                mArrayList);
        M1.setAdapter(arrayAdapter1);

        ListView I1;
        I1 = (ListView) view.findViewById(R.id.I1);
        ArrayList<String> iArrayList = new ArrayList<>();
        iArrayList.add("Ounce");
        iArrayList.add("Pound");
        iArrayList.add("Ton");
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                iArrayList);
        I1.setAdapter(arrayAdapter2);

        ListView M2;
        M2 = (ListView) view.findViewById(R.id.M2);
        M2.setAdapter(arrayAdapter1);

        ListView I2;
        I2 = (ListView) view.findViewById(R.id.I2);
        I2.setAdapter(arrayAdapter2);


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
                if(Double.parseDouble(input.getText().toString()) > 1.0){
                    unit += "s";
                }
                unit1.setText(unit);
            }
        });
        I1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l) {
                TextView unit1 = view.findViewById(R.id.Unit1);
                String unit = iArrayList.get(i);
                EditText input = view.findViewById(R.id.input);
                if(input.getText().toString().equals("") || input.getText().toString().equals(".")){
                    Toast.makeText(getActivity(), "Enter a valid number first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.parseDouble(input.getText().toString()) > 1.0){
                    if(i == 0){
                        unit += "s";
                    }else if(i == 1){
                        unit += "s";
                    }else if(i == 2){
                        unit += "s";
                    }

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
                    case "Milligram":
                    case "Milligrams":
                        result = number / Math.pow(10,i);
                        break;
                    case "Centigram":
                    case "Centigrams":
                        result = number / Math.pow(10,i - 1);
                        break;
                    case "Decigram":
                    case "Decigrams":
                        result = number / Math.pow(10,i - 2);
                        break;
                    case "Gram":
                    case "Grams":
                        result = number / Math.pow(10,i - 3 );
                        break;
                    case "Decagram":
                    case "Decagrams":
                        result = number / Math.pow(10,i - 4);
                        break;
                    case "Hectogram":
                    case "Hectograms":
                        result = number / Math.pow(10,i - 5 );
                        break;
                    case "Kilogram":
                    case "Kilograms":
                        result = number / Math.pow(10,i - 6);
                        break;
                    case "Ounce":
                    case "Ounces":
                        number = number * 28349.5;
                        result = number / Math.pow(10,i);
                        break;
                    case "Pound":
                    case "Pounds":
                        number = number * 453592;
                        result = number / Math.pow(10,i);
                        break;
                    case "Ton":
                    case "Tons":
                        number = number * 1.016 * Math.pow(10,9);
                        result = number / Math.pow(10,i);
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
                if(b.stripTrailingZeros().doubleValue() > 1.0){
                    unit += "s";
                }
                unit2.setText(unit);
            }
        });
        I2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                String unit = iArrayList.get(i);

                //Calculate Answer
                double number = Double.parseDouble(input.getText().toString());
                TextView answer = view.findViewById(R.id.Answer);
                double result = 0;
                switch (unit1.getText().toString()){
                    case "Milligram":
                    case "Milligrams":
                        if(i == 0){
                            result = number * 3.5274 * Math.pow(10,-5);
                        }else if(i == 1){
                            result = number * 2.20462 * Math.pow(10,-6);
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-10);
                        }
                        break;
                    case "Centigram":
                    case "Centigrams":
                        if(i == 0){
                            result = number * .00035274;
                        }else if(i == 1){
                            result = number * 2.20462 * Math.pow(10,-5) ;
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-9);
                        }
                        break;
                    case "Decigram":
                    case "Decigrams":
                        if(i == 0){
                            result = number * .0035274;
                        }else if(i == 1){
                            result = number * .000220462;
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-8);
                        }
                        break;
                    case "Gram":
                    case "Grams":
                        if(i == 0){
                            result = number * .035274;
                        }else if(i == 1){
                            result = number * .00220462;
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-7);
                        }
                        break;
                    case "Decagram":
                    case "Decagrams":
                        if(i == 0){
                            result = number * .35274;
                        }else if(i == 1){
                            result = number * .0220462;
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-6);
                        }
                        break;
                    case "Hectogram":
                    case "Hectograms":
                        if(i == 0){
                            result = number * 3.5274;
                        }else if(i == 1){
                            result = number * .220462;
                        }else if(i == 2){
                            result = number * 9.8421 * Math.pow(10,-5);
                        }
                        break;
                    case "Kilogram":
                    case "Kilograms":
                        if(i == 0){
                            result = number * 35.274;
                        }else if(i == 1){
                            result = number * 2.20462;
                        }else if(i == 2){
                            result = number * .000984207;
                        }
                        break;
                    case "Ounce":
                    case "Ounces":
                        if(i == 0){
                            result = number;
                        }else if(i == 1){
                            result = number * .0625;
                        }else if(i == 2){
                            result = number * 2.7902 * Math.pow(10,-5);
                        }
                        break;
                    case "Pound":
                    case "Pounds":
                        if(i == 0){
                            result = number * 16;
                        }else if(i == 1){
                            result = number;
                        }else if(i == 2){
                            result = number * .000446429;
                        }
                        break;
                    case "Ton":
                    case "Tons":
                        if(i == 0){
                            result = number * 35840;
                        }else if(i == 1){
                            result = number * 2240;
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
                if(b.stripTrailingZeros().doubleValue() > 1.0){
                    if(i == 0){
                        unit += "s";
                    }else if(i == 1){
                        unit += "s";
                    }else if(i == 2){
                        unit += "s";
                    }else{
                        unit += "s";
                    }

                }
                unit2.setText(unit);

            }
        });
        return view;
    }
}
