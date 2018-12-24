package com.projectxr.mehmetd.bulentbey.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.projectxr.mehmetd.bulentbey.R;


public class TabFragment11 extends Fragment implements View.OnClickListener {

    Button prfl,sslktp,ektp,ar,iltsm;

    public TabFragment11() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment11, container, false);

        prfl= view.findViewById(R.id.profilButton);
        sslktp= view.findViewById(R.id.sesliKitapButton);
        ektp= view.findViewById(R.id.eKitapButton);
        ar= view.findViewById(R.id.ARButton);
        iltsm= view.findViewById(R.id.iletisimButton);


        prfl.setOnClickListener(this);
        sslktp.setOnClickListener(this);
        ektp.setOnClickListener(this);
        ar.setOnClickListener(this);
        iltsm.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.profilButton:
                loadFragment(new tab_fragment4()); break;
            case R.id.sesliKitapButton:
                loadFragment(new TabFragment22()); break;
            case R.id.eKitapButton:
                Toast.makeText(getActivity().getApplicationContext(), "Ülkenizde kullanıma uygun değildir.",Toast.LENGTH_LONG).show();
                break;
            case R.id.ARButton:
                Toast.makeText(getActivity().getApplicationContext(), "Yakında hizmetinizde olacaktır.",Toast.LENGTH_LONG).show();
                break;
            case R.id.iletisimButton:
                loadFragment(new TabFragment33());
                break;



        }

    }
    private boolean loadFragment(Fragment fragment) {
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
