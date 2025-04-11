package com.example.neurocare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PacientAdapter extends RecyclerView.Adapter<PacientAdapter.ViewHolder> {
    private final List<String> pacienti;

    public PacientAdapter(List<String> pacienti) {
        this.pacienti = pacienti;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(pacienti.get(position));
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
