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
import com.projectxr.mehmetd.bulentbey.LoginActivity;
import com.projectxr.mehmetd.bulentbey.Models.LoginResponse;
import com.projectxr.mehmetd.bulentbey.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GirisFragment extends Fragment {

 EditText idd,password;
    SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);

        String kullanıcı =sharedPreferences.getString("oauth_key", "kullanıcı");

        if (!kullanıcı.equals("kullanıcı"))
        {
            startActivity(new Intent(getActivity(),BaseActivity.class));

        }



        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giris, container, false);
    }

    public void login() {
        String id = idd.getText().toString().trim();
        String password2=password.getText().toString().trim();

        RetrofitService retrofitService= RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
        Call<LoginResponse> call =retrofitService.login(id,password2);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                startActivity(new Intent(getActivity(),BaseActivity.class));

                sharedPreferences.edit().putString("oauth_key", response.body().getKey().toString()).commit();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });




    }

    public void LoginMethod(View view) {
        login();
    }

}
