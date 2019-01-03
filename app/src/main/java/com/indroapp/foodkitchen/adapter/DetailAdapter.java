package com.indroapp.foodkitchen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indroapp.foodkitchen.R;
import com.indroapp.foodkitchen.model.DetailModel;
import com.indroapp.foodkitchen.model.MyDetail;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Holder> {
    private ArrayList<MyDetail> list;

    public DetailAdapter(ArrayList<MyDetail> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_detail, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        MyDetail model = list.get(i);

        holder.nama.setText(model.getNama());
        holder.jum.setText(model.getJum());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView nama, jum;

        public Holder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            jum = itemView.findViewById(R.id.jum);
        }
    }
}
