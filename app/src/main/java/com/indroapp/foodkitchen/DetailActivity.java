package com.indroapp.foodkitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.indroapp.foodkitchen.adapter.DetailAdapter;
import com.indroapp.foodkitchen.model.DetailModel;
import com.indroapp.foodkitchen.model.MyDetail;
import com.indroapp.foodkitchen.presenter.MainPresenter;
import com.indroapp.foodkitchen.service.API;
import com.indroapp.foodkitchen.view.DetailPesanView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DetailActivity extends AppCompatActivity implements DetailPesanView {
    TextView nama, jum;
    Button btDimasak, btSelesai, btCancel;
    MainPresenter mainPresenter;
    String id;
    RecyclerView rv;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama = findViewById(R.id.nama);
        jum = findViewById(R.id.jum);
        btDimasak = findViewById(R.id.btDimasak);
        btSelesai = findViewById(R.id.btSelesai);
        btCancel = findViewById(R.id.btCancel);
        rv = findViewById(R.id.rvDet);

        id = getIntent().getStringExtra("id");
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.getDetailPesan(API.GET_DETAIL_PESANAN+id);

        btDimasak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                updateStatus(status);
            }
        });
        btSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                updateStatus(status);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 3;
                updateStatus(status);
            }
        });
    }

    @Override
    public void onSuccesGetDetail(ArrayList<MyDetail> list) {
        DetailAdapter adapter = new DetailAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public void onSuccesUpdate(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void updateStatus(int status){
        String s = String.valueOf(status);
        mainPresenter.updateStatus(API.UPDATE_STATUS, id, s);
    }
}
