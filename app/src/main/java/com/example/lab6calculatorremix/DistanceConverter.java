package com.example.lab6calculatorremix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab6calculatorremix.databinding.DistanceConverterFragmentBinding;


public class DistanceConverter extends Fragment {

    private DistanceConverterFragmentBinding binding;

    private EditText mileInput;
    private EditText kilometerInput;

    public DistanceConverter() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DistanceConverterFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mileInput = binding.mileInput;
        kilometerInput = binding.kilometerInput;

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mileText = mileInput.getText().toString();
                boolean mileEmpty = mileText.isEmpty();
                String kilometerText = kilometerInput.getText().toString();
                boolean kilometerEmpty = kilometerText.isEmpty();

                if (mileEmpty && kilometerEmpty) {
                    Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean sameValue = mileText.equals(kilometerText);

                if (!mileEmpty && mileInput.isFocused() || sameValue) {
                    try {
                        double miles = Double.parseDouble(mileText);
                        double kilometers = miles * 1.60934;
                        kilometerInput.setText(String.format("%.2f", kilometers));
                    }
                    catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else if (!kilometerEmpty && kilometerInput.isFocused()) {
                    try {
                        double kilometers = Double.parseDouble(kilometerText);
                        double miles = kilometers / 1.60934;
                        mileInput.setText(String.format("%.2f", miles));
                    }
                    catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }
}