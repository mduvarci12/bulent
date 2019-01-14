package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.Models.MessageResponse;
import com.projectxr.mehmetd.bulentbey.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabFragment33 extends Fragment {
SharedPreferences sharedPreferences;
String oauthKey;
    EditText ad,no,mail,msg;
    Button yolla;

    public TabFragment33() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
      View view = inflater.inflate(R.layout.fragment_tab_fragment33, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);

      ad = view.findViewById(R.id.isimSoyisim);
      no= view.findViewById(R.id.telno);
      mail = view.findViewById(R.id.mail);
      msg = view.findViewById(R.id.mesajım);

      yolla=view.findViewById(R.id.yollaB);
      yolla.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
              NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

              if (!(networkInfo != null && networkInfo.isConnected() ))
              {
                  Toast.makeText(getActivity(),"Internet bağlantınızı kontrol edin",Toast.LENGTH_LONG).show();
              }

              mesajyolla();

          }
      });



      return view;
    }

    private void mesajyolla() {
        oauthKey = "bearer " + sharedPreferences.getString("oauth_key", "bulunamadı");
    RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);


        Call<MessageResponse> call = retrofitService.msgSend(oauthKey,msg.getText().toString(),ad.getText().toString(),no.getText().toString(),mail.getText().toString());
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                if (response.body().getStatus().equals("Success")){
                    Toast.makeText(getActivity(),"İletiniz Gönderildi",Toast.LENGTH_LONG).show();
                    msg.setText(" ");
                    ad.setText(" ");
                    no.setText(" ");
                    mail.setText(" ");
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {

            }
        });
    }

}

