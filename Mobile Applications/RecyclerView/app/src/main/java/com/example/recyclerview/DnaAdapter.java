package com.example.recyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DnaAdapter extends RecyclerView.Adapter<DnaAdapter.DnaViewHolder> {


    private final DnaAdapterOnClickHandler clickHandler;
    private List<DNA> dnas;

    public interface DnaAdapterOnClickHandler{
        void onClick(DNA dna);
    }

    public DnaAdapter(DnaAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public DnaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        DnaViewHolder viewHolder = new DnaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DnaViewHolder holder, int position) {
        DNA dna = dnas.get(position);
        String title = dna.getTitle();
        String date = dna.getDate();
        holder.titleTextView.setText(title);
        //holder.dateTextView.setText(date);

    }

    @Override
    public int getItemCount() {
        if (dnas != null) return dnas.size();
        return 0;
    }

    public void setDnaData(List<DNA> dnasData){
        dnas = dnasData;
        notifyDataSetChanged();
    }

    class DnaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView titleTextView;
        //private final TextView dateTextView;

        public DnaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text);
            //dateTextView = itemView.findViewById(R.id.date_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPos = getAdapterPosition();
            DNA dna = dnas.get(adapterPos);
            clickHandler.onClick(dna);
        }
    }
}


