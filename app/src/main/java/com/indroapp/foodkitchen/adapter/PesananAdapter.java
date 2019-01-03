package com.indroapp.foodkitchen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indroapp.foodkitchen.R;
import com.indroapp.foodkitchen.model.PesananModel;
import com.indroapp.foodkitchen.utils.FormatRupiah;

import java.util.ArrayList;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.Holder> {
    private ArrayList<PesananModel> pesananModels;
    private PesananSelect listener;

    public PesananAdapter(ArrayList<PesananModel> pesananModels, PesananSelect listener) {
        this.pesananModels = pesananModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_pesanan, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        PesananModel model = pesananModels.get(i);
        final String id  = String.valueOf(model.getPesananId());
        String status;
        switch (model.getPesananStatus()){
            case 1: status="sedang dimasak";break;
            case 2: status="selesai dimasak";break;
            case 3: status="order batal";break;
            default: status="antrian order";break;
        }

        holder.meja.setText("No meja : "+String.valueOf(model.getNoMeja()));
        holder.tgl.setText(model.getPesananTgl());
        holder.status.setText("Status : "+status);
        holder.total.setText(FormatRupiah.formatRupiah(model.getPesananTotal()));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPesananClick(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pesananModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tgl, meja, status, total;
        CardView item;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tgl = itemView.findViewById(R.id.tgl);
            meja = itemView.findViewById(R.id.meja);
            status = itemView.findViewById(R.id.status);
            total = itemView.findViewById(R.id.total);
            item = itemView.findViewById(R.id.card);
        }
    }

    public interface PesananSelect{
        void onPesananClick(String id);
    }
}
