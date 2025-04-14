package com.example.neurocare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PacientAdapter extends RecyclerView.Adapter<PacientAdapter.ViewHolder> {

    private final List<Pacient> pacientList;
    private final OnPacientActionListener listener;

    public PacientAdapter(List<Pacient> pacientList, OnPacientActionListener listener) {
        this.pacientList = pacientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pacient pacient = pacientList.get(position);
        holder.textViewName.setText("Nume: " + pacient.getNume());
        holder.textViewAge.setText("Varsta: " + pacient.getVarsta());
        holder.textViewDiagnostic.setText("Diagnostic: " + pacient.getDiagnostic());
        holder.textViewTratament.setText("Tratament: " + pacient.getTratament());
        holder.textViewObservatii.setText("Observatii: " + pacient.getObservatii());

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetaliiPacientActivity.class);
            intent.putExtra("pacient", pacient);
            context.startActivity(intent);
        });

        holder.imageViewMenu.setOnClickListener(v -> showPopupMenu(v, pacient, position));
    }

    private void showPopupMenu(View view, Pacient pacient, int position) {
        Context context = view.getContext();
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_pacient, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_edit) {
                if (listener != null) {
                    listener.onEditPacient(pacient, position);
                }
                return true;
            } else if (item.getItemId() == R.id.menu_delete) {
                deletePacientFromFirestore(pacient, position, context);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private void deletePacientFromFirestore(Pacient pacient, int position, Context context) {
        FirebaseFirestore.getInstance().collection("Pacienti")
                .document(pacient.getId()) // asigură-te că `Pacient` are un câmp `id`
                .delete()
                .addOnSuccessListener(aVoid -> {
                    pacientList.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Pacient sters", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Eroare la stergere", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public int getItemCount() {
        return pacientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewAge, textViewDiagnostic, textViewTratament, textViewObservatii;
        ImageView imageViewMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewDiagnostic = itemView.findViewById(R.id.textViewDiagnostic);
            textViewTratament = itemView.findViewById(R.id.textViewTratament);
            textViewObservatii = itemView.findViewById(R.id.textViewObservatii);
            imageViewMenu = itemView.findViewById(R.id.imageViewMenu);
        }
    }

    // INTERFAȚĂ – necesară pentru editare
    public interface OnPacientActionListener {
        void onEditPacient(Pacient pacient, int position);
    }
}
