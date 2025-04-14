package com.example.neurocare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgramareAdapter extends RecyclerView.Adapter<ProgramareAdapter.ViewHolder> {
    private final List<Programare> programari;

    public ProgramareAdapter(List<Programare> programari) {
        this.programari = programari;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programare, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Programare p = programari.get(position);
        holder.textNume.setText(p.getNumePacient());
        holder.textData.setText("Data: " + p.getData());
        holder.textOra.setText("Ora: " + p.getOra());
        holder.textScop.setText("Scop: " + p.getScop());
    }

    @Override
    public int getItemCount() {
        return programari.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNume, textData, textOra, textScop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNume = itemView.findViewById(R.id.textViewNume);
            textData = itemView.findViewById(R.id.textViewDateTime);
            textOra = itemView.findViewById(R.id.textViewOra);
            textScop = itemView.findViewById(R.id.editTextScopProgramare);
        }
    }
}
