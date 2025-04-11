package com.example.neurocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class PacientiFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pacienti, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        List<String> pacienti = Arrays.asList(
                "Popescu Ionut", "Iota Cristina", "Adam Cristina", "Munteanu Andreea",
                "Mitroi Adina", "Vlad Dumitru", "Enache Cristian"
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new PacientAdapter(pacienti));

        return view;
    }
}
