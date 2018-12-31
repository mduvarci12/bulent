package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.BuyActivity;
import com.projectxr.mehmetd.bulentbey.Models.Kitap;
import com.projectxr.mehmetd.bulentbey.Models.PartResponse;
import com.projectxr.mehmetd.bulentbey.Models.ProfileResponse;
import com.projectxr.mehmetd.bulentbey.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class listFragment extends Fragment {

ImageButton buymore;
    String oauthKey;
    SharedPreferences sharedPreferences;
    String pos;
    public String[] partlar;
    ListView listView;

    public listFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView=getActivity().findViewById(R.id.sesList);
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        buymore =view.findViewById(R.id.purchImage);
        buymore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(), BuyActivity.class);
                startActivity(i);

            }
        });
        bolumGetir(pos,oauthKey);


return view;


    }


    @Override
    public void  onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listView=getActivity().findViewById(R.id.sesList);
        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        pos = sharedPreferences.getString("position","bulunamadı");

        oauthKey = "bearer " + sharedPreferences.getString("oauth_key", "bulunamadı");


        RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
        Call<ProfileResponse> call = retrofitService.profilCall(oauthKey);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.body().getUserType().equals("1"))
              buymore.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });


    }






    private void bolumGetir(String i,String bearer) {

        RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
        Call<PartResponse> call = retrofitService.partGetir(i,bearer);
        call.enqueue(new Callback<PartResponse>() {
            @Override
            public void onResponse(Call<PartResponse> call, Response<PartResponse> response) {
                final List<Kitap> kitapList =response.body().getKitap();

                partlar = new String[kitapList.size()];

                for (int c=0;c<kitapList.size();c++){
                    partlar[c]=kitapList.get(c).getKitapAdi().toString();
                } listView=getActivity().findViewById(R.id.sesList);
                ArrayAdapter<String > adapter = new ArrayAdapter<>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, partlar);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                       sharedPreferences.edit().putString("partUrl", ""+kitapList.get(position).getKitapUrl()).commit();


                       sharedPreferences.edit().putString("partNo", ""+position).commit();

                       Fragment fragment=null;
                       fragment = new PlayerFragment();
                       loadFragment(fragment);
                   }
               });
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PartResponse> call, Throwable t) {

            }
        });


    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    }

