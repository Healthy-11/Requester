package com.example.requester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder>{
    private final Context context;
    List<Provider> list;

    public ProviderAdapter(Context context) {
        this.context = context;
        this.list = DatabaseManager.getInstance(context).providerDao().getAll();
    }

    @NonNull
    @Override
    public ProviderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_row,parent, false);
        return new ProviderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderAdapter.ViewHolder holder, final int position) {

        holder.fonom.setText(list.get(position).getForef() + " " + list.get(position).getFonom());
        holder.foadresse.setText(list.get(position).getFoad() + ", " + list.get(position).getFoville() + " - " + list.get(position).getFocp());
        holder.fotel.setText(list.get(position).getFoemail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fonom;
        TextView foadresse;
        TextView fotel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fonom = (TextView) itemView.findViewById(R.id.textViewFoprenom);
            foadresse = (TextView) itemView.findViewById(R.id.textViewFoAdresse);
            fotel = (TextView) itemView.findViewById(R.id.textViewFoteldom);

        }
    }
}
