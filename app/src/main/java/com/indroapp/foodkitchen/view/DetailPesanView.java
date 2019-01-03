package com.indroapp.foodkitchen.view;

import com.indroapp.foodkitchen.model.DetailModel;
import com.indroapp.foodkitchen.model.MyDetail;

import java.util.ArrayList;
import java.util.List;

public interface DetailPesanView {
    void onSuccesGetDetail(ArrayList<MyDetail> list);
    void onSuccesUpdate(String msg);
}
