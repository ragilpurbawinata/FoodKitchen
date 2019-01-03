package com.indroapp.foodkitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.indroapp.foodkitchen.adapter.PesananAdapter;
import com.indroapp.foodkitchen.model.PesananModel;
import com.indroapp.foodkitchen.presenter.MainPresenter;
import com.indroapp.foodkitchen.service.API;
import com.indroapp.foodkitchen.view.PesananView;

import java.util.ArrayList;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements PesananView, PesananAdapter.PesananSelect {
    RecyclerView rvPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPesanan = findViewById(R.id.rvPesanan);

        MainPresenter mainPresenter = new MainPresenter(this, this);
        mainPresenter.getPesanan(API.GET_PESANAN);
    }

    @Override
    public void onPesananClick(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onSuccesGetPesanan(ArrayList<PesananModel> pesananModels) {
        PesananAdapter adapter = new PesananAdapter(pesananModels, this);
        rvPesanan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPesanan.setAdapter(adapter);
    }
}
