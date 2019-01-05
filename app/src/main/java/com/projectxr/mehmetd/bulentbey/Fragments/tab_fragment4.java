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
import android.widget.TextView;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.LauncherActivity;
import com.projectxr.mehmetd.bulentbey.Models.ProfileResponse;
import com.projectxr.mehmetd.bulentbey.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class tab_fragment4 extends Fragment {

    SharedPreferences sharedPreferences;
    String oauthKey;
    Button b;

    TextView adText,epostaText,kullaniciText;
    String ad,eposta,text;

    public tab_fragment4() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        oauthKey = "bearer " + sharedPreferences.getString("oauth_key", "bulunamadı");



        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_fragment4, container, false);

        adText = view.findViewById(R.id.kullanıcıAdıText);
        epostaText = view.findViewById(R.id.EpostaText);
        kullaniciText = view.findViewById(R.id.KullanıcıTuruText);
        b = view.findViewById(R.id.logoutButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sharedPreferences.edit().remove("oauth_key").commit();
                Intent i = new Intent(getActivity(), LauncherActivity.class);
                startActivity(i);
            }
        });

        RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
        Call<ProfileResponse> call = retrofitService.profilCall(oauthKey);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();

                ad = profileResponse.getUsername();
                eposta = profileResponse.getEmail();
                text = profileResponse.getUserType();

                switch (text){
                    case "0" : text="Standart Kullanıcı"; break;
                    case "1" : text="Sesli Kitap Kullanıcısı"; break;
                    case "2" : text="Artırılmış Gerçeklik Kullanıcısı"; break;
                    default:   text="Standart Kullanıcı"; break;

                }
                adText.setText(ad);
                epostaText.setText(eposta);
                kullaniciText.setText(text);

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });



        return view;
    }


}
