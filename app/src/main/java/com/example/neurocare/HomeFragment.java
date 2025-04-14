package com.example.neurocare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView textViewDateTime;
    private TextView textViewPatientCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewDateTime = view.findViewById(R.id.textViewDateTime);
        textViewPatientCount = view.findViewById(R.id.textViewPatientCount);

        // Logout
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        updateDateTime();
        updatePatientCount();
    }

    // 🔁 Se apelează automat când fragmentul revine în prim-plan
    @Override
    public void onResume() {
        super.onResume();
        updateDateTime();
        updatePatientCount();
    }

    private void updateDateTime() {
        String currentDateTime = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(new Date());
        if (textViewDateTime != null) {
            textViewDateTime.setText(currentDateTime);
        }
    }

    private void updatePatientCount() {
        FirebaseFirestore.getInstance().collection("Pacienti")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    int count = queryDocumentSnapshots.size();
                    if (textViewPatientCount != null) {
                        textViewPatientCount.setText("Aveți " + count + " pacienți sub observație.");
                    }
                })
                .addOnFailureListener(e -> {
                    if (textViewPatientCount != null) {
                        textViewPatientCount.setText("Eroare la încărcarea pacienților.");
                    }
                });
    }
}
