package com.example.neurocare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetaliiPacientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_pacient);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textView = findViewById(R.id.textViewDetalii);
        String numePacient = getIntent().getStringExtra("nume_pacient");

        if (numePacient != null) {
            textView.setText("Detalii pentru: " + numePacient);
        }
    }
}
