package com.example.neurocare;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddAppointmentActivity extends AppCompatActivity {

    private EditText numePacient, data, scop;
    private TextView oraText;
    private String oraFinala = "";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        numePacient = findViewById(R.id.editTextNume);
        data = findViewById(R.id.editTextData);
        scop = findViewById(R.id.editTextScop);
        oraText = findViewById(R.id.textViewOra);
        Button buttonOra = findViewById(R.id.buttonOra);
        Button buttonSalveaza = findViewById(R.id.buttonSalveaza);

        db = FirebaseFirestore.getInstance();

        // Dacă activitatea primește o dată selectată din calendar
        String dataPrimita = getIntent().getStringExtra("data_selectata");
        if (dataPrimita != null && !dataPrimita.isEmpty()) {
            data.setText(dataPrimita);
        }

        buttonOra.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int h = c.get(Calendar.HOUR_OF_DAY);
            int m = c.get(Calendar.MINUTE);

            new TimePickerDialog(this, (view, hour, minute) -> {
                oraFinala = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                oraText.setText("Ora selectată: " + oraFinala);
            }, h, m, true).show();
        });

        buttonSalveaza.setOnClickListener(v -> {
            String nume = numePacient.getText().toString().trim();
            String ziua = data.getText().toString().trim();
            String motiv = scop.getText().toString().trim();

            if (nume.isEmpty() || ziua.isEmpty() || motiv.isEmpty() || oraFinala.isEmpty()) {
                Toast.makeText(this, "Completează toate câmpurile!", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> programare = new HashMap<>();
            programare.put("numePacient", nume);
            programare.put("data", ziua);
            programare.put("ora", oraFinala);
            programare.put("scop", motiv);

            db.collection("Programari").add(programare)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Programare salvată!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Eroare la salvare!", Toast.LENGTH_SHORT).show());
        });
    }
}
