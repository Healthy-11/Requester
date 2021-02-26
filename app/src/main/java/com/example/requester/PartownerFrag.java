package com.example.requester;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PartownerFrag extends Fragment implements TabNotifier{


    RecyclerView elementList;
    PartownerAdapter adapter;

    public PartownerFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.partowner_fragment, container, false);

        elementList = view.findViewById(R.id.POelement_list);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        elementList.setLayoutManager(llm);

        adapter = new PartownerAdapter(this.getContext());
        elementList.setAdapter(adapter);
        return view;
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }
}
