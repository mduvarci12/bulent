package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectxr.mehmetd.bulentbey.R;


public class PlayerFragment extends Fragment {
    SharedPreferences sharedPreferences;

    public PlayerFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        String partNo =sharedPreferences.getString("partNo","bulunamadı");
        String partUrl= sharedPreferences.getString("partUrl", "bulunamadı");
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

    }
}
