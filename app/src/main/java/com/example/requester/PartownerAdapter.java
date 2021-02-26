package com.example.requester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartownerAdapter extends RecyclerView.Adapter<PartownerAdapter.ViewHolder> {
    private final Context context;
    List<Partowner> list;

    public PartownerAdapter(Context context) {
        this.context = context;
        this.list = DatabaseManager.getInstance(context).partownerDao().getAll();
    }

    @NonNull
    @Override
    public PartownerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.partowner_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nom.setText(list.get(position).getPetitre() + ", " + list.get(position).getPenom() + " " +  list.get(position).getPeprenom());
        holder.voirienom.setText(list.get(position).getPevoirienom());
        holder.adresse.setText("Adress : " + list.get(position).getPeadresse() + ", " + list.get(position).getPeville());
        holder.tel.setText("Contact : " + list.get(position).getPeteldom() + ", " + list.get(position).getPeemail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom;
        TextView voirienom;
        TextView adresse;
        TextView tel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.textViewPeprenom);
            voirienom = (TextView) itemView.findViewById(R.id.textViewPevoirirenom);
            adresse = (TextView) itemView.findViewById(R.id.textViewPeAdresse);
            tel = (TextView) itemView.findViewById(R.id.textViewPeteldom);

        }
    }
}
