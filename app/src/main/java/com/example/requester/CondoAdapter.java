package com.example.requester;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CondoAdapter extends RecyclerView.Adapter<CondoAdapter.ViewHolder> implements Filterable{
    private final Context context;
    List<Condo> list;
    List<Condo> listFull;

    public CondoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CondoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.condo_row, parent, false);
        return new ViewHolder(view);
    }

    public void fillList() {
        this.list = DatabaseManager.getInstance(context).condoDao().getAll();
        listFull = new ArrayList<>(list);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.id.setText(list.get(position).getServerId());
        holder.societe.setText(list.get(position).getSociete());
        holder.cleunik.setText(list.get(position).getCleunik());
        holder.copro.setText(list.get(position).getCopro());
        holder.nom.setText(list.get(position).getNom());
        holder.cp.setText(list.get(position).getCp());
        holder.ville.setText(list.get(position).getVille());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, InfoActivity.class);
                String condoNum = list.get(position).getCopro();
                myIntent.putExtra("condoNum", condoNum);
                context.startActivity(myIntent);

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Please confirm...");
                builder.setMessage("Delete ?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Condo d = list.get(holder.getAdapterPosition());
                        DatabaseManager.getInstance(context).condoDao().delete(d);
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });

    }

    public void NotifyDataChanged() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView societe;
        TextView cleunik;
        TextView copro;
        TextView nom;
        TextView cp;
        TextView ville;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textViewId);
            societe = (TextView) itemView.findViewById(R.id.textViewSociete);
            cleunik = (TextView) itemView.findViewById(R.id.textViewCleunik);
            copro = (TextView) itemView.findViewById(R.id.textViewCopro);
            nom = (TextView) itemView.findViewById(R.id.textViewNom);
            cp = (TextView) itemView.findViewById(R.id.textViewCp);
            ville = (TextView) itemView.findViewById(R.id.textViewVille);

        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Condo> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Condo condo : listFull) {
                    if (condo.getNom().toLowerCase().contains(filterPattern)){
                        filteredList.add(condo);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
