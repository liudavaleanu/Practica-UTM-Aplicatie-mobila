package com.example.neurocare;

import android.app.Application;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamplePatientsInitializer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<Map<String, Object>> examplePatients = Arrays.asList(
                create("Elena Popescu", "Boala Alzheimer, stadiu moderat", "Donepezil 10mg/zi", 68, "Confuzie spațio-temporală, dificultăți de recunoaștere a membrilor familiei."),
                create("Andrei Vasilescu", "Scleroză multiplă recurent-remisivă", "Interferon beta-1a", 52, "Episoade de oboseală severă și tulburări de vedere."),
                create("Maria Ionescu", "Parkinson, stadiu II", "Levodopa/Carbidopa 3x/zi", 73, "Tremor la nivelul membrelor superioare, rigiditate musculară progresivă."),
                create("Cristian Marinescu", "Epilepsie de lob temporal", "Carbamazepină 400mg/zi", 59, "Crize epileptice parțiale complexe, ușoare modificări de personalitate."),
                create("Adriana Neagu", "Demență vasculară", "Memantină 20mg/zi", 80, "Tulburări de memorie severă, dificultăți în gestionarea activităților zilnice.")
        );

        for (Map<String, Object> pacient : examplePatients) {
            db.collection("pacienti")
                    .whereEqualTo("nume", pacient.get("nume"))
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        if (querySnapshot.isEmpty()) {
                            db.collection("pacienti").add(pacient);
                        }
                    });
        }
    }

    private Map<String, Object> create(String nume, String diagnostic, String tratament, int varsta, String observatii) {
        Map<String, Object> pacient = new HashMap<>();
        pacient.put("nume", nume);
        pacient.put("diagnostic", diagnostic);
        pacient.put("tratament", tratament);
        pacient.put("varsta", varsta);
        pacient.put("observatii", observatii);
        return pacient;
    }
}
