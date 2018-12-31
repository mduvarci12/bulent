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
import android.widget.Button;
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
    String id, password2;
    Button girisYap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);

        String kullanici =sharedPreferences.getString("oauth_key", "kullanıcı");

        if (!kullanici.equals("kullanıcı"))
        {
           startActivity(new Intent(getActivity(),BaseActivity.class));

        }

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giris, container, false);
        idd=view.findViewById(R.id.id);
        password=view.findViewById(R.id.password);

        girisYap = view.findViewById(R.id.Loginbutton);
        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        return view;
    }

    public void login() {
        id = idd.getText().toString().trim();
     password2=password.getText().toString().trim();
        if (id.isEmpty()) {
            idd.setError("Kullanıcı adı giriniz");
            idd.requestFocus();
            return;
        }

        if (password2.isEmpty()) {
            password.setError("Şifrenizi Giriniz");
            password.requestFocus();
            return;
        }

        if (password2.length() < 4) {
            password.setError("Şifreniz en az altı karakter uzunluğunda olmalıdır");
            password.requestFocus();
            return;
        }


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


}
