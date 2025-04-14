package com.example.neurocare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private FloatingActionButton addEventButton;
    private RecyclerView recyclerView;
    private ProgramareAdapter adapter;
    private List<Programare> programariList = new ArrayList<>();
    private FirebaseFirestore db;
    private String dataSelectata;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private final HashSet<String> dateCuProgramari = new HashSet<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.calendarView);
        addEventButton = view.findViewById(R.id.addEventButton);
        recyclerView = view.findViewById(R.id.recyclerViewProgramari);

        db = FirebaseFirestore.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProgramareAdapter(programariList);
        recyclerView.setAdapter(adapter);

        dataSelectata = sdf.format(new Date());
        loadProgramari();
        markDatesWithProgramari();

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            dataSelectata = sdf.format(calendar.getTime());
            loadProgramari();
        });

        addEventButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddAppointmentActivity.class);
            intent.putExtra("data_selectata", dataSelectata);
            startActivity(intent);
        });
    }

    private void loadProgramari() {
        db.collection("Programari")
                .whereEqualTo("data", dataSelectata)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    programariList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Programare programare = doc.toObject(Programare.class);
                        programariList.add(programare);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Eroare la încărcarea programărilor.", Toast.LENGTH_SHORT).show();
                });
    }

    private void markDatesWithProgramari() {
        db.collection("Programari")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    dateCuProgramari.clear();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        String data = doc.getString("data");
                        if (data != null) {
                            dateCuProgramari.add(data);
                        }
                    }

                    calendarView.setDate(new Date().getTime(), false, true); // reset ca să declanșeze refresh vizual
                });
    }
}
