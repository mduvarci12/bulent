package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.BaseActivity;
import com.projectxr.mehmetd.bulentbey.Models.RegisterResponse;
import com.projectxr.mehmetd.bulentbey.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KayitFragment extends Fragment {
    EditText soyisim, id, mail, tel, sifre, isim;
    String ad, soyAd, username, eposta, telefon, pw;
    Button kayitOlButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_kayit, container, false);


        isim = (EditText) view.findViewById(R.id.isim);
        soyisim = view.findViewById(R.id.soyisim);
        id = view.findViewById(R.id.kullanıcıAdı);
        mail = view.findViewById(R.id.eposta);
        tel = view.findViewById(R.id.telefon);
        sifre = view.findViewById(R.id.sifre);
        kayitOlButton= view.findViewById(R.id.uyeolButton);


        kayitOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitOl();
            }
        });

        return view;

    }

    private void kayitOl() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (!(networkInfo != null && networkInfo.isConnected() ))
        {
            Toast.makeText(getActivity(),"Internet bağlantınızı kontrol edin",Toast.LENGTH_LONG).show();
        }


        RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);

        ad = isim.getText().toString().trim();
        soyAd = soyisim.getText().toString().trim();
        username = id.getText().toString().trim();
        eposta = mail.getText().toString().trim();
        telefon = tel.getText().toString().trim();
        pw = sifre.getText().toString().trim();

        Call<RegisterResponse> call = retrofitService.Register(username, pw, eposta, telefon, ad, soyAd, "application/x-www-form-urlencoded");
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                startActivity(new Intent(getActivity(), BaseActivity.class));

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }


}
