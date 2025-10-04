package com.example.probna_kart12;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.probna_kart12.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private int liczba;
    private ArrayAdapter arrayAdapter;
    private ArrayList<Integer> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        lista = new ArrayList<>();



        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                lista

        );

        binding.wyniki.setAdapter(arrayAdapter);

        binding.buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int id = binding.spinner.getSelectedItemPosition();
                switch(id){
                    case 0:
                        liczba = Integer.parseInt(binding.editTextText.getText().toString().trim());
                        lista.add(silnia(liczba));
                        arrayAdapter.notifyDataSetChanged();
                        break;

                    case 1:
                        liczba = Integer.parseInt(binding.editTextText.getText().toString().trim());
                        lista.add(sumacyfr(liczba));
                        arrayAdapter.notifyDataSetChanged();
                        break;

                    case 2:
                        liczba = Integer.parseInt(binding.editTextText.getText().toString().trim());
                        if(czypierwsza(liczba)){
                            binding.czytrue.setText("Liczba " + liczba + " jest liczbą pierwszą");
                        }
                        else{
                            binding.czytrue.setText("Liczba " + liczba + " nie jest liczbą pierwszą");
                        }

                        break;
                }
            }
        });


    }

    private int silnia(int n){
        if (n == 0) {
            return 1;
        } else {
            return (n * silnia(n - 1));
        }
    }

    private Boolean czypierwsza(int liczba){
        if (liczba <= 1) return false;
        boolean liczby_pierwsze[] = new boolean[liczba + 1];
        Arrays.fill(liczby_pierwsze,true);
        liczby_pierwsze[0] = false;
        liczby_pierwsze[1] = false;
        for (int i = 2; i * i <= liczba; i++) {
            if (liczby_pierwsze[i]) {
                for (int t = i * i; t <= liczba; t += i) {
                    liczby_pierwsze[t] = false;
                }
            }
        }

        return liczby_pierwsze[liczba];

    }

    private int sumacyfr(int liczba){
        if(liczba > 0 && liczba < 10){
            return liczba;
        }
        else{
            int suma = 0;
            String znaki = Integer.toString(liczba);
            for (int i = 0;i<znaki.length();i++) {
                suma += Character.getNumericValue(znaki.charAt(i));
            }
            return suma;
        }
    }

}