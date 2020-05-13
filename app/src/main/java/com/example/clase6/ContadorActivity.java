package com.example.clase6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clase6.ViewModels.ContadorViewModel;

public class ContadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        final TextView textViewContador = findViewById(R.id.textViewContador);

        ContadorViewModel contadorViewModel = new ViewModelProvider(this).get(ContadorViewModel.class);

        contadorViewModel.getContador().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textViewContador.setText(String.valueOf(integer));
            }
        });

    }

    public void iniciarContadorBtn(View view){
        ContadorViewModel contadorViewModel = new ViewModelProvider(this).get(ContadorViewModel.class);
        contadorViewModel.iniciarContador();
    }

    public void detenerContadorBtn(View view){
        ContadorViewModel contadorViewModel = new ViewModelProvider(this).get(ContadorViewModel.class);
contadorViewModel.detenerContador();
    }
}
