package com.example.neurocare;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neurocare.Pacient;
import com.example.neurocare.R;

public class DialogUtil {

    public interface OnPatientUpdatedListener {
        void onPatientUpdated(Pacient pacient);
    }

    public static void showPatientDialog(Context context, Pacient pacient, OnPatientUpdatedListener listener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_edit_pacient, null);

        EditText editTextNume = dialogView.findViewById(R.id.editNume);
        EditText editTextVarsta = dialogView.findViewById(R.id.editVarsta);
        EditText editTextDiagnostic = dialogView.findViewById(R.id.editTextDiagnostic);
        EditText editTextTratament = dialogView.findViewById(R.id.editTextTratament);
        EditText editTextObservatii = dialogView.findViewById(R.id.editTextObservatii);

        if (editTextNume == null || editTextVarsta == null || editTextDiagnostic == null ||
                editTextTratament == null || editTextObservatii == null) {
            Toast.makeText(context, "Eroare la încărcarea formularului!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pacient != null) {
            editTextNume.setText(pacient.getNume());
            editTextVarsta.setText(pacient.getVarsta());
            editTextDiagnostic.setText(pacient.getDiagnostic());
            editTextTratament.setText(pacient.getTratament());
            editTextObservatii.setText(pacient.getObservatii());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView)
                .setTitle(pacient == null ? "Adaugă pacient" : "Editează pacient")
                .setPositiveButton("Salvează", (dialog, which) -> {
                    String nume = editTextNume.getText().toString().trim();
                    String varsta = editTextVarsta.getText().toString().trim();
                    String diagnostic = editTextDiagnostic.getText().toString().trim();
                    String tratament = editTextTratament.getText().toString().trim();
                    String observatii = editTextObservatii.getText().toString().trim();

                    if (nume.isEmpty() || varsta.isEmpty()) {
                        Toast.makeText(context, "Completează numele și vârsta!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Pacient updatedPacient = new Pacient(nume, varsta, diagnostic, tratament, observatii);
                    if (pacient != null) {
                        updatedPacient.setId(pacient.getId()); // păstrăm ID-ul
                    }
                    listener.onPatientUpdated(updatedPacient);
                })
                .setNegativeButton("Anulează", null)
                .create()
                .show();
    }
}
