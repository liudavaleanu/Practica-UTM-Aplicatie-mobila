package com.example.neurocare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class TesteFragment extends Fragment {

    private final String[] teste = {
            "UPDRS - specific Parkinson", "MOCA - specific Alzheimer",
            "Joc cu cărți", "MMSE - pentru memorie",
            "CDT - abilități vizuo-spațiale", "ACE - demență"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teste, container, false);
        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, teste);
        listView.setAdapter(adapter);
        return view;
    }
}
