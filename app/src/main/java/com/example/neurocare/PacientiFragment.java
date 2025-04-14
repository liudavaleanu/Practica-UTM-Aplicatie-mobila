package com.example.neurocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class PacientiFragment extends Fragment implements PacientAdapter.OnPacientActionListener {

    private FirebaseFirestore db;
    private CollectionReference pacientRef;
    private ArrayList<Pacient> pacientList;
    private PacientAdapter adapter;
    private TextView textEmptyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pacienti, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton addPatientButton = view.findViewById(R.id.addPatientButton);
        textEmptyList = view.findViewById(R.id.textEmptyList);

        db = FirebaseFirestore.getInstance();
        pacientRef = db.collection("Pacienti");
        pacientList = new ArrayList<>();
        adapter = new PacientAdapter(pacientList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        loadPacienti();

        addPatientButton.setOnClickListener(v -> showAddEditDialog(null, -1));

        return view;
    }

    private void loadPacienti() {
        pacientRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            pacientList.clear();
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                Pacient pacient = doc.toObject(Pacient.class);
                pacient.setId(doc.getId()); // setează ID-ul
                pacientList.add(pacient);
            }
            adapter.notifyDataSetChanged();
            textEmptyList.setVisibility(pacientList.isEmpty() ? View.VISIBLE : View.GONE);
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "Eroare la încărcarea pacienților", Toast.LENGTH_SHORT).show();
        });
    }

    public void showAddEditDialog(Pacient pacient, int position) {
        com.example.neurocare.DialogUtil.showPatientDialog(requireContext(), pacient, updatedPacient -> {
            if (pacient == null) {
                // Adaugă nou
                pacientRef.add(updatedPacient).addOnSuccessListener(docRef -> {
                    updatedPacient.setId(docRef.getId());
                    pacientList.add(updatedPacient);
                    adapter.notifyItemInserted(pacientList.size() - 1);
                    textEmptyList.setVisibility(View.GONE);
                });
            } else {
                // Editează
                pacientRef.document(pacient.getId()).set(updatedPacient).addOnSuccessListener(aVoid -> {
                    updatedPacient.setId(pacient.getId());
                    pacientList.set(position, updatedPacient);
                    adapter.notifyItemChanged(position);
                });
            }
        });
    }

    @Override
    public void onEditPacient(Pacient pacient, int position) {
        showAddEditDialog(pacient, position);
    }
}
