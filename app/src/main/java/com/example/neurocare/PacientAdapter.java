package com.example.neurocare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PacientAdapter extends RecyclerView.Adapter<PacientAdapter.ViewHolder> {
    private final List<String> pacienti;
    private Context context = null;

    public PacientAdapter(List<String> pacienti) {
        this.pacienti = pacienti;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext(); // inițializare context aici
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String pacient = pacienti.get(position);
        holder.nameText.setText(pacient);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetaliiPacientActivity.class);
            intent.putExtra("nume_pacient", pacient);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pacienti.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.examName);
        }
    }
}
