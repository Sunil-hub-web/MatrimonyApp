package com.example.matrimonyapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.databinding.FragmentRegister10Binding;

public class HealthDetailsFragment extends Fragment {

    FragmentRegister10Binding binding;

    RadioButton selectedRadioButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_register10,container,false);

        binding = FragmentRegister10Binding.inflate(inflater,container,false);

        binding.radioDisability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                String text = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        binding.readioDisease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                String text = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });


        return binding.getRoot();
    }
}
