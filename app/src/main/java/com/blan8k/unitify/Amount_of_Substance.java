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

public class Amount_of_Substance extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.amount_of_substance, container, false);

        ListView M1;
        M1 = (ListView) view.findViewById(R.id.M1);
        ArrayList<String> mArrayList = new ArrayList<>();
        mArrayList.add("Milliliter");
        mArrayList.add("Centiliter");
        mArrayList.add("Deciliter");
        mArrayList.add("Liter");
        mArrayList.add("Decaliter");
        mArrayList.add("Hectoliter");
        mArrayList.add("Kiloliter");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                mArrayList);
        M1.setAdapter(arrayAdapter1);

        ListView I1;
        I1 = (ListView) view.findViewById(R.id.I1);
        ArrayList<String> iArrayList = new ArrayList<>();
        iArrayList.add("Teaspoon");
        iArrayList.add("Tablespoon");
        iArrayList.add("Ounce");
        iArrayList.add("Cup");
        iArrayList.add("Pint");
        iArrayList.add("Quart");
        iArrayList.add("Gallon");
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
                if (input.getText().toString().equals("") || input.getText().toString().equals(".")) {
                    Toast.makeText(getActivity(), "Enter a valid number first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(input.getText().toString()) > 1.0) {
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
                if (input.getText().toString().equals("") || input.getText().toString().equals(".")) {
                    Toast.makeText(getActivity(), "Enter a valid number first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(input.getText().toString()) > 1.0) {
                    unit += "s";
                }
                unit1.setText(unit);
            }
        });
        M2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l) {
                TextView unit1 = view.findViewById(R.id.Unit1);
                EditText input = view.findViewById(R.id.input);
                if (unit1.getText().toString().equals("") || input.getText().toString().equals(".")
                        || input.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Fill in the initial values first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                TextView unit2 = view.findViewById(R.id.Unit2);
                String unit = mArrayList.get(i);
                //Calculate Answer
                double number = Double.parseDouble(input.getText().toString());
                TextView answer = view.findViewById(R.id.Answer);
                double result = 0;
                switch (unit1.getText().toString()) {
                    case "Milliliter":
                    case "Milliliters":
                        result = number / Math.pow(10, i);
                        break;
                    case "Centiliter":
                    case "Centiliters":
                        result = number / Math.pow(10, i - 1);
                        break;
                    case "Deciliter":
                    case "Deciliters":
                        result = number / Math.pow(10, i - 2);
                        break;
                    case "Liter":
                    case "Liters":
                        result = number / Math.pow(10, i - 3);
                        break;
                    case "Decaliter":
                    case "Decaliters":
                        result = number / Math.pow(10, i - 4);
                        break;
                    case "Hectoliter":
                    case "Hectoliters":
                        result = number / Math.pow(10, i - 5);
                        break;
                    case "Kiloliter":
                    case "Kiloliters":
                        result = number / Math.pow(10, i - 6);
                        break;
                    case "Teaspoon":
                    case "Teaspoons":
                        number = number * 4.92892;
                        result = number / Math.pow(10, i);
                        break;
                    case "Tablespoon":
                    case "Tablespoons":
                        number = number * 14.7868;
                        result = number / Math.pow(10, i);
                        break;
                    case "Ounce":
                    case "Ounces":
                        number = number * 29.5735;
                        result = number / Math.pow(10, i);
                        break;
                    case "Cup":
                    case "Cups":
                        number = number * 240;
                        result = number / Math.pow(10, i);
                        break;
                    case "Pint":
                    case "Pints":
                        number = number * 473.176;
                        result = number / Math.pow(10, i);
                        break;
                    case "Quart":
                    case "Quarts":
                        number = number * 946.353;
                        result = number / Math.pow(10, i);
                        break;
                    case "Gallon":
                    case "Gallons":
                        number = number * 3785.41;
                        result = number / Math.pow(10, i);
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
                if (b.stripTrailingZeros().doubleValue() > 1.0) {
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
                if (unit1.getText().toString().equals("") || input.getText().toString().equals(".")
                        || input.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Fill in the initial values first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                TextView unit2 = view.findViewById(R.id.Unit2);
                String unit = iArrayList.get(i);

                //Calculate Answer
                double number = Double.parseDouble(input.getText().toString());
                TextView answer = view.findViewById(R.id.Answer);
                double result = 0;
                switch (unit1.getText().toString()) {
                    case "Milliliter":
                    case "Milliliters":
                        if (i == 0) {
                            result = number / 4.92892;
                        } else if (i == 1) {
                            result = number / 14.7868;
                        } else if (i == 2) {
                            result = number / 29.5735;
                        } else if (i == 3) {
                            result = number / 240;
                        }else if(i == 4){
                            result = number / 473.176;
                        }else if(i == 5){
                            result = number / 946.353;
                        }else if(i == 6){
                            result = number / 3785.41;
                        }
                        break;
                    case "Centiliter":
                    case "Centiliters":
                        if (i == 0) {
                            result = number / .492892;
                        } else if (i == 1) {
                            result = number / 1.47868;
                        } else if (i == 2) {
                            result = number / 2.95735;
                        } else if (i == 3) {
                            result = number / 24;
                        }else if(i == 4){
                            result = number / 47.3176;
                        }else if(i == 5){
                            result = number / 94.6353;
                        }else if(i == 6){
                            result = number / 378.541;
                        }
                        break;
                    case "Deciliter":
                    case "Deciliters":
                        if (i == 0) {
                            result = number / .0492892;
                        } else if (i == 1) {
                            result = number / .147868;
                        } else if (i == 2) {
                            result = number / .295735;
                        } else if (i == 3) {
                            result = number / 2.4;
                        }else if(i == 4){
                            result = number / 4.73176;
                        }else if(i == 5){
                            result = number / 9.46353;
                        }else if(i == 6){
                            result = number / 37.8541;
                        }
                        break;
                    case "Liter":
                    case "Liters":
                        if (i == 0) {
                            result = number / .00492892;
                        } else if (i == 1) {
                            result = number / .0147868;
                        } else if (i == 2) {
                            result = number / .0295735;
                        } else if (i == 3) {
                            result = number / .24;
                        }else if(i == 4){
                            result = number / .473176;
                        }else if(i == 5){
                            result = number / .946353;
                        }else if(i == 6){
                            result = number / 3.78541;
                        }
                        break;
                    case "Decaliter":
                    case "Decaliters":
                        if (i == 0) {
                            result = number / .000492892;
                        } else if (i == 1) {
                            result = number / .00147868;
                        } else if (i == 2) {
                            result = number / .00295735;
                        } else if (i == 3) {
                            result = number / .024;
                        }else if(i == 4){
                            result = number / .0473176;
                        }else if(i == 5){
                            result = number / .0946353;
                        }else if(i == 6){
                            result = number / .378541;
                        }
                        break;
                    case "Hectoliter":
                    case "Hectoliters":
                        if (i == 0) {
                            result = number / .0000492892;
                        } else if (i == 1) {
                            result = number/.000147868;
                        } else if (i == 2) {
                            result = number / .000295735;
                        } else if (i == 3) {
                            result = number / .0024;
                        }else if(i == 4){
                            result = number / .00473176;
                        }else if(i == 5){
                            result = number / .00946353;
                        }else if(i == 6){
                            result = number / .0378541;
                        }
                        break;
                    case "Kiloliter":
                    case "Kiloliters":
                        if (i == 0) {
                            result = number / .00000492892;
                        } else if (i == 1) {
                            result = number / .0000147868;
                        } else if (i == 2) {
                            result = number / .0000295735;
                        } else if (i == 3) {
                            result = number / .00024;
                        }else if(i == 4){
                            result = number / .000473176;
                        }else if(i == 5){
                            result = number / .000946353;
                        }else if(i == 6){
                            result = number / .00378541;
                        }
                        break;
                    case "Teaspoon":
                    case "Teaspoons":
                        if (i == 0) {
                            result = number;
                        } else if (i == 1) {
                            result = number / 3;
                        } else if (i == 2) {
                            result = number / 6;
                        } else if (i == 3) {
                            result = number / 48.6922;
                        }else if(i == 4){
                            result = number / 96;
                        }else if(i == 5){
                            result = number / 192;
                        }else if(i == 6){
                            result = number / 768;
                        }
                        break;
                    case "Tablespoon":
                    case "Tablespoons":
                        if (i == 0) {
                            result = number*3;
                        } else if (i == 1) {
                            result = number;
                        } else if (i == 2) {
                            result = number / 2;
                        } else if (i == 3) {
                            result = number / 16.2307;
                        }else if(i == 4){
                            result = number / 32;
                        }else if(i == 5){
                            result = number / 64;
                        }else if(i == 6){
                            result = number / 256;
                        }
                        break;
                    case "Ounce":
                    case "Ounces":
                        if (i == 0) {
                            result = number/6;
                        } else if (i == 1) {
                            result = number*2;
                        } else if (i == 2) {
                            result = number;
                        } else if (i == 3) {
                            result = number / 8.11537;
                        }else if(i == 4){
                            result = number / 16;
                        }else if(i == 5){
                            result = number / 32;
                        }else if(i == 6){
                            result = number / 128;
                        }
                        break;
                    case "Cup":
                    case "Cups":
                        if (i == 0) {
                            result = number / .0205372;
                        } else if (i == 1) {
                            result = number / .0616115;
                        } else if (i == 2) {
                            result = number / .123223;
                        } else if (i == 3) {
                            result = number;
                        }else if(i == 4){
                            result = number / 1.97157;
                        }else if(i == 5){
                            result = number / 3.94314;
                        }else if(i == 6){
                            result = number / 15.7725;
                        }
                        break;
                    case "Pint":
                    case "Pints":
                        if (i == 0) {
                            result = number * 96;
                        } else if (i == 1) {
                            result = number *32;
                        } else if (i == 2) {
                            result = number *16;
                        } else if (i == 3) {
                            result = number / .50721;
                        }else if(i == 4){
                            result = number;
                        }else if(i == 5){
                            result = number / 2;
                        }else if(i == 6){
                            result = number / 8;
                        }
                        break;
                    case "Quart":
                    case "Quarts":
                        if (i == 0) {
                            result = number * 192;
                        } else if (i == 1) {
                            result = number * 64;
                        } else if (i == 2) {
                            result = number * 32;
                        } else if (i == 3) {
                            result = number / .253605;
                        }else if(i == 4){
                            result = number * 2;
                        }else if(i == 5){
                            result = number;
                        }else if(i == 6){
                            result = number / 4;
                        }
                        break;
                    case "Gallon":
                    case "Gallons":
                        if (i == 0) {
                            result = number / .00130208;
                        } else if (i == 1) {
                            result = number * 256;
                        } else if (i == 2) {
                            result = number * 128;
                        } else if (i == 3) {
                            result = number / .0634013;
                        }else if(i == 4){
                            result = number * 8;
                        }else if(i == 5){
                            result = number * 4;
                        }else if(i == 6){
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
                if (b.stripTrailingZeros().doubleValue() > 1.0) {
                    unit += "s";

                }
                unit2.setText(unit);

            }
        });
        return view;
    }
}
