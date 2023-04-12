package com.example.lab6calculatorremix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab6calculatorremix.databinding.TipCalculatorFragmentBinding;
import com.google.android.material.textfield.TextInputEditText;

public class TipCalculator extends Fragment {

    private TipCalculatorFragmentBinding binding;

    public TipCalculator() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = TipCalculatorFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.totalBillInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextInputEditText editText = binding.totalBillInput;
                String totalBillInput = editText.getText().toString();

                if (totalBillInput.isEmpty() || totalBillInput.equals("0")) {
                    binding.output.setText(getResources().getString(R.string.invalidInput));
                } else {

                    try {
                        double totalBill = Double.parseDouble(totalBillInput);
                    } catch (NumberFormatException e) {
                        binding.output.setText(getResources().getString(R.string.invalidInput));
                    }
                }
            }
        });

        binding.tipPercentageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextInputEditText editText = binding.tipPercentageInput;
                String tipPercentageInput = editText.getText().toString();

                if (tipPercentageInput.isEmpty() || tipPercentageInput.equals("0")) {
                    binding.output.setText(getResources().getString(R.string.invalidInput));
                }
                try {
                    double totalTip = Double.parseDouble(tipPercentageInput);
                } catch (NumberFormatException e) {
                    binding.output.setText(getResources().getString(R.string.invalidInput));
                }
            }
        });

        binding.numberOfPeopleInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextInputEditText editText = binding.numberOfPeopleInput;
                String numberOfPeopleInput = editText.getText().toString();

                if (numberOfPeopleInput.isEmpty() || numberOfPeopleInput.equals("0")) {
                    binding.output.setText(getResources().getString(R.string.invalidInput));
                }
                try {
                    double totalPeople = Double.parseDouble(numberOfPeopleInput);
                } catch (NumberFormatException e) {
                    binding.output.setText(getResources().getString(R.string.invalidInput));
                }
            }
        });

        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalBillText = binding.totalBillInput.getText().toString();
                String totalTipText = binding.tipPercentageInput.getText().toString();
                String totalPeopleText = binding.numberOfPeopleInput.getText().toString();

                if (checkForZero(totalBillText, totalTipText, totalPeopleText)) {
                    Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                try {
                    double totalBill = Double.parseDouble(binding.totalBillInput.getText().toString());
                    double totalTip = Double.parseDouble(binding.tipPercentageInput.getText().toString());
                    double totalPeople = Double.parseDouble(binding.numberOfPeopleInput.getText().toString());

                    double result = calculateOutput(totalBill, totalTip, totalPeople);
                    binding.output.setText(String.format("$%.2f", result));

                } catch ( NumberFormatException e) {
                    if (totalBillText.isEmpty() || totalTipText.isEmpty() || totalPeopleText.isEmpty()) {
                        Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });
    }

    private boolean checkForZero(String totalBillText, String totalTipText, String totalPeopleText) {
        return totalBillText.equals("0") || totalTipText.equals("0") || totalPeopleText.equals("0");
    }

    private double calculateOutput(double totalBill, double totalTip, double totalPeople) {
        double tip = totalBill * (totalTip / 100);
        double totalAndTip = totalBill + tip;
        return totalAndTip / totalPeople;
    }
}



