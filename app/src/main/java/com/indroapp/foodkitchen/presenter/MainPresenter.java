package com.indroapp.foodkitchen.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.indroapp.foodkitchen.model.DetailModel;
import com.indroapp.foodkitchen.model.MyDetail;
import com.indroapp.foodkitchen.model.PesananModel;
import com.indroapp.foodkitchen.model.ResponUpdate;
import com.indroapp.foodkitchen.service.AppController;
import com.indroapp.foodkitchen.utils.DialogUtil;
import com.indroapp.foodkitchen.view.DetailPesanView;
import com.indroapp.foodkitchen.view.PesananView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class MainPresenter {
    public Context context;
    private PesananView pesananView;
    private DetailPesanView detailPesanView;

    public MainPresenter(Context context, PesananView pesananView) {
        this.context = context;
        this.pesananView = pesananView;
    }

    public MainPresenter(Context context, DetailPesanView detailPesanView) {
        this.context = context;
        this.detailPesanView = detailPesanView;
    }

    public void getPesanan(String url){
        DialogUtil.showProgressDialog(context, "Memuat pesanan...");
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<PesananModel> list = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PesananModel model = new PesananModel();
                                model.setPesananId(jsonObject.getInt("pesanan_id"));
                                model.setNoMeja(jsonObject.getInt("no_meja"));
                                model.setPesananTgl(jsonObject.getString("pesanan_tgl"));
                                model.setPesananStatus(jsonObject.getInt("pesanan_status"));
                                model.setPesananTotal(jsonObject.getInt("pesanan_total"));
                                list.add(model);
                            }
                            pesananView.onSuccesGetPesanan(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        DialogUtil.dialogDismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }

    public void getDetailPesan(String url){
        DialogUtil.showProgressDialog(context, "Memuat detail...");
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Timber.e(response);

                        ArrayList<MyDetail> list = new ArrayList<>();
                        DetailModel detailModel = new Gson().fromJson(response, DetailModel.class);
                        String[] makananS = detailModel.getMakanan().split("_");
                        String[] minumanS = detailModel.getMinuman().split("_");
                        String[] makananJ = detailModel.getJmlMakanan().split("_");
                        String[] minumanJ = detailModel.getJmlMinuman().split("_");

                        for (int i=0;i<makananS.length;i++){
                            MyDetail detail = new MyDetail();
                            detail.setNama(makananS[i]);
                            detail.setJum(makananJ[i]);
                            list.add(detail);
                        }
                        for (int i=0;i<minumanS.length;i++){
                            MyDetail detail = new MyDetail();
                            detail.setNama(minumanS[i]);
                            detail.setJum(minumanJ[i]);
                            list.add(detail);
                        }
                        DialogUtil.dialogDismiss();
                        detailPesanView.onSuccesGetDetail(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }

    public void updateStatus(String url, final String id, final String status){
        DialogUtil.showProgressDialog(context, "Update status pesanan...");
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponUpdate responUpdate = new Gson().fromJson(response, ResponUpdate.class);
                        if (!responUpdate.getError()){
                            detailPesanView.onSuccesUpdate(responUpdate.getMessage());
                            DialogUtil.dialogDismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id_pesan", id);
                params.put("status_pesan", status);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
}
