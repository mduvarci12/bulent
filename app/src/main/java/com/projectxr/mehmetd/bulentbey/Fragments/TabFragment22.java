package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.KitapAdapter;
import com.projectxr.mehmetd.bulentbey.Models.KitapItem;
import com.projectxr.mehmetd.bulentbey.Models.Kitaplar;
import com.projectxr.mehmetd.bulentbey.Models.KitaplarResponse;
import com.projectxr.mehmetd.bulentbey.OnAdapterClickListener;
import com.projectxr.mehmetd.bulentbey.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabFragment22 extends Fragment {
    int myPos;
    listFragment listFragmentonject = new listFragment();

    SharedPreferences sharedPreferences;
    String oauthKey;

    private List<KitapItem> kitapItems;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    Fragment nexf = new listFragment();


    public TabFragment22() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        FragmentManager fm = getFragmentManager();
        return inflater.inflate(R.layout.fragment_tab_fragment22, container, false);



    }

    @Override
    public void onStart() {

        super.onStart();
        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        oauthKey = "bearer " + sharedPreferences.getString("oauth_key", "bulunamadı");
        recyclerView= getActivity().findViewById(R.id.kitaplarRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        kitapItems = new ArrayList<>();
        RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);

        Call<KitaplarResponse> call = retrofitService.kitaplar(oauthKey);
        call.enqueue(new Callback<KitaplarResponse>() {
            @Override
            public void onResponse(Call<KitaplarResponse> call, Response<KitaplarResponse> response) {
                List<Kitaplar> kitaplarResponseList = response.body().getKitaplar();

                for (int i=0;i<kitaplarResponseList.size();i++){

                    Kitaplar kitap = kitaplarResponseList.get(i);
                    KitapItem kitapItem =new KitapItem(kitap.getKitapAdi(), kitap.getKitapResmi(), kitap.getId());
                    kitapItems.add(kitapItem);
                }

                adapter = new KitapAdapter(kitapItems,getActivity().getApplicationContext(), new OnAdapterClickListener() {
                    @Override
                    public void onItemClickListener(int pos) {
                        ++pos;
                        sharedPreferences.edit().putString("position", ""+pos).commit();

                        Fragment fragment=null;
                        fragment = new listFragment();
                        loadFragment(fragment);

                    }
                });
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<KitaplarResponse> call, Throwable t) {

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
