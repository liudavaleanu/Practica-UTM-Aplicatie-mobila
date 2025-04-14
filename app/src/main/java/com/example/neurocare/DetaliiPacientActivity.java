package com.example.neurocare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetaliiPacientActivity extends AppCompatActivity {

    private TextView textViewNume;
    private TextView textViewDiagnostic;
    private TextView textViewTratament;
    private TextView textViewVarsta;
    private TextView textViewObservatii;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_pacient); // Verifică să ai acest layout cu numele corect

        textViewNume = findViewById(R.id.detaliiNume);
        textViewDiagnostic = findViewById(R.id.detaliiDiagnostic);
        textViewTratament = findViewById(R.id.detaliiTratament);
        textViewVarsta = findViewById(R.id.detaliiVarsta);
        textViewObservatii = findViewById(R.id.detaliiObservatii);
        backButton = findViewById(R.id.backButton);

        // 🔙 Logica butonului de întoarcere
        backButton.setOnClickListener(v -> finish());

        // 🧠 Preluăm obiectul pacient din intent și afișăm datele
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("pacient")) {
            Pacient pacient = (Pacient) intent.getSerializableExtra("pacient");

            if (pacient != null) {
                textViewNume.setText("Nume: " + pacient.getNume());
                textViewDiagnostic.setText("Diagnostic: " + pacient.getDiagnostic());
                textViewTratament.setText("Tratament: " + pacient.getTratament());
                textViewVarsta.setText("Vârstă: " + pacient.getVarsta());
                textViewObservatii.setText("Observații: " + pacient.getObservatii());
            }
        }
    }
}
