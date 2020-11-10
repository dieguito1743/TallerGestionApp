package com.buildingdreamcars.tallergestionapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.buildingdreamcars.tallergestionapp.DatosTemp.Preferencias;
import com.buildingdreamcars.tallergestionapp.R;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWelcome = findViewById(R.id.txtwelcome);
        tvWelcome.setText("Bienvenido seas "+ Preferencias.getNickname(getApplicationContext())+". Parece que " +
                "todavía estamos en proceso de crear esta app. Por favor se paciente");
    }
}