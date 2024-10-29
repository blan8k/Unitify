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

public class Length extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.length, container, false);

        ListView M1;
        M1 = (ListView) view.findViewById(R.id.M1);
        ArrayList<String> mArrayList = new ArrayList<>();
        mArrayList.add("Millimeter");
        mArrayList.add("Centimeter");
        mArrayList.add("Decimeter");
        mArrayList.add("Meter");
        mArrayList.add("Decameter");
        mArrayList.add("Hectometer");
        mArrayList.add("Kilometer");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                mArrayList);
        M1.setAdapter(arrayAdapter1);

        ListView I1;
        I1 = (ListView) view.findViewById(R.id.I1);
        ArrayList<String> iArrayList = new ArrayList<>();
        iArrayList.add("Inch");
        iArrayList.add("Foot");
        iArrayList.add("Yard");
        iArrayList.add("Mile");
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
                        unit += "es";
                    }else if(i == 1){
                        unit = "Feet";
                    }else if(i == 2){
                        unit += "s";
                    }else{
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
                    case "Millimeter":
                    case "Millimeters":
                        result = number / Math.pow(10,i);
                        break;
                    case "Centimeter":
                    case "Centimeters":
                        result = number / Math.pow(10,i - 1);
                        break;
                    case "Decimeter":
                    case "Decimeters":
                        result = number / Math.pow(10,i - 2);
                        break;
                    case "Meter":
                    case "Meters":
                        result = number / Math.pow(10,i - 3 );
                        break;
                    case "Decameter":
                    case "Decameters":
                        result = number / Math.pow(10,i - 4);
                        break;
                    case "Hectometer":
                    case "Hectometers":
                        result = number / Math.pow(10,i - 5 );
                        break;
                    case "Kilometer":
                    case "Kilometers":
                        result = number / Math.pow(10,i - 6);
                        break;
                    case "Inch":
                    case "Inches":
                        number = number * 25.4;
                        result = number / Math.pow(10,i);
                        break;
                    case "Foot":
                    case "Feet":
                        number = number * 304.8;
                        result = number / Math.pow(10,i);
                        break;
                    case "Yard":
                    case "Yards":
                        number = number * 914.4;
                        result = number / Math.pow(10,i);
                        break;
                    case "Mile":
                    case "Miles":
                        number = number * 1609344;
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
                    case "Millimeter":
                    case "Millimeters":
                        if(i == 0){
                            result = number * .0393701;
                        }else if(i == 1){
                            result = number * 0.00328084;
                        }else if(i == 2){
                            result = number * 0.0010936133333333;
                        }else if(i == 3){
                            result = number / 1609344;
                        }
                        break;
                    case "Centimeter":
                    case "Centimeters":
                        if(i == 0){
                            result = number / 2.54;
                        }else if(i == 1){
                            result = number /30.48 ;
                        }else if(i == 2){
                            result = number /91.44;
                        }else if(i == 3){
                            result = number / 160934;
                        }
                        break;
                    case "Decimeter":
                    case "Decimeters":
                        if(i == 0){
                            result = number * 3.93701;
                        }else if(i == 1){
                            result = number * 0.328084;
                        }else if(i == 2){
                            result = number * 0.109361;
                        }else if(i == 3){
                            result = number / 16093;
                        }
                        break;
                    case "Meter":
                    case "Meters":
                        if(i == 0){
                            result = number * 39.3701;
                        }else if(i == 1){
                            result = number * 3.28084;
                        }else if(i == 2){
                            result = number * 1.09361;
                        }else if(i == 3){
                            result = number / 1609;
                        }
                        break;
                    case "Decameter":
                    case "Decameters":
                        if(i == 0){
                            result = number * 393.701;
                        }else if(i == 1){
                            result = number * 32.8084;
                        }else if(i == 2){
                            result = number * 10.9361;
                        }else if(i == 3){
                            result = number / 161;
                        }
                        break;
                    case "Hectometer":
                    case "Hectometers":
                        if(i == 0){
                            result = number * 3937;
                        }else if(i == 1){
                            result = number * 328.084;
                        }else if(i == 2){
                            result = number * 109.361;
                        }else if(i == 3){
                            result = number * .0621371;
                        }
                        break;
                    case "Kilometer":
                    case "Kilometers":
                        if(i == 0){
                            result = number * 39370.1;
                        }else if(i == 1){
                            result = number * 3280.84;
                        }else if(i == 2){
                            result = number * 1093.61;
                        }else if(i == 3){
                            result = number * .621371;
                        }
                        break;
                    case "Inch":
                    case "Inches":
                        if(i == 0){
                            result = number;
                        }else if(i == 1){
                            result = number / 12;
                        }else if(i == 2){
                            result = number /36;
                        }else if(i == 3){
                            result = number / 63360;
                        }
                        break;
                    case "Foot":
                    case "Feet":
                        if(i == 0){
                            result = number * 12;
                        }else if(i == 1){
                            result = number;
                        }else if(i == 2){
                            result = number/3;
                        }else if(i == 3){
                            result = number / 5280;
                        }
                        break;
                    case "Yard":
                    case "Yards":
                        if(i == 0){
                            result = number * 36;
                        }else if(i == 1){
                            result = number * 3;
                        }else if(i == 2){
                            result = number;
                        }else if(i == 3){
                            result = number / 1760 ;
                        }
                        break;
                    case "Mile":
                    case "Miles":
                        if(i == 0){
                            result = number * 63360;
                        }else if(i == 1){
                            result = number * 5280;
                        }else if(i == 2){
                            result = number * 1760;
                        }else if(i == 3){
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
                        unit += "es";
                    }else if(i == 1){
                        unit = "Feet";
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
