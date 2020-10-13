package com.example.ubermap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class TelaPrincipal extends AppCompatActivity {

    private Button btnMotorista, btnPassageiro;
    private TextInputEditText inputEmail, inputSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btnMotorista = findViewById(R.id.Motoristabtn);
        btnPassageiro = findViewById(R.id.Passageirobtn);

        inputEmail = findViewById(R.id.email);
        inputSenha = findViewById(R.id.senha);


        btnPassageiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        btnMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Motorista.class));
            }
        });



    }
}