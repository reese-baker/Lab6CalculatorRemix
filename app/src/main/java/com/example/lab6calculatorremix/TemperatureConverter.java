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

import com.example.lab6calculatorremix.databinding.TemperatureConverterFragmentBinding;


public class TemperatureConverter extends Fragment {

    private TemperatureConverterFragmentBinding binding;

    private EditText fahrenheitInput;
    private EditText celsiusInput;

    public TemperatureConverter() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = TemperatureConverterFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fahrenheitInput = binding.fahrenheitInput;
        celsiusInput = binding.celsiusInput;

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                String fahrenheitText = fahrenheitInput.getText().toString();
                boolean fahrenheitEmpty = fahrenheitText.isEmpty();
                String celsiusText = celsiusInput.getText().toString();
                boolean celsiusEmpty = celsiusText.isEmpty();

                if (fahrenheitEmpty && celsiusEmpty) {
                    Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                    toast.show();
                }

                boolean sameValue = fahrenheitText.equals(celsiusText);

                if (!fahrenheitInput.getText().toString().isEmpty() && fahrenheitInput.isFocused() || sameValue) {
                    try {
                        double fahrenheit = Double.parseDouble(fahrenheitInput.getText().toString());
                        double celsius = (fahrenheit - 32) * 5 / 9;
                        celsiusInput.setText(String.format("%.2f", celsius));
                    }
                    catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(binding.getRoot().getContext(), "Invalid Input", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else if (!celsiusInput.getText().toString().isEmpty() && celsiusInput.isFocused()) {
                    try {
                        double celsius = Double.parseDouble(celsiusInput.getText().toString());
                        double fahrenheit = celsius * 9 / 5 + 32;
                        fahrenheitInput.setText(String.format("%.2f", fahrenheit));
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
