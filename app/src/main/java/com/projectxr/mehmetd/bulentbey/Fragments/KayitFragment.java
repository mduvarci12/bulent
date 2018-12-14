package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.BaseActivity;
import com.projectxr.mehmetd.bulentbey.Models.RegisterResponse;
import com.projectxr.mehmetd.bulentbey.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KayitFragment extends Fragment {

    SharedPreferences sharedPreferences;
    EditText isim,soyisim,id,mail,tel,sifre;
    String ad, soyAd, username,eposta,telefon,pw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kayit, container, false);
        isim = view.findViewById(R.id.isim);
        soyisim = view.findViewById(R.id.soyisim);
        id= view.findViewById(R.id.kullanıcıAdı);
        mail= view.findViewById(R.id.eposta);
        tel= view.findViewById(R.id.telefon);
        sifre = view.findViewById(R.id.sifre);

         ad = isim.getText().toString().trim();
         soyAd = soyisim.getText().toString().trim();
         username = id.getText().toString().trim();
         eposta = mail.getText().toString().trim();
         telefon= tel.getText().toString().trim();
         pw = sifre.getText().toString().trim();



        return view;
    }

    private void kayitOl() {

        RetrofitService retrofitService= RetrofitClient.getRetrofitInstance().create(RetrofitService.class);

        Call<RegisterResponse> call= retrofitService.Register(username,pw,eposta,telefon,ad,soyAd, "application/x-www-form-urlencoded");
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                startActivity(new Intent(getActivity(),BaseActivity.class));

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }

    public void kayitOL(View view) {
        kayitOl();
    }
}
