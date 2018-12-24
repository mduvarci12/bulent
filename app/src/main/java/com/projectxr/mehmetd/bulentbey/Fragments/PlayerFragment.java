package com.projectxr.mehmetd.bulentbey.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.projectxr.mehmetd.bulentbey.BaseActivity;
import com.projectxr.mehmetd.bulentbey.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class PlayerFragment extends Fragment {
    MixpanelAPI mixpanel;

    SharedPreferences sharedPreferences;
    ImageButton playButton, pauseButton;
    TextView partName, sec, secTotal;
    ImageView bookImage;
    SeekBar seekBar;
    MediaPlayer mp;

    String partUrl;
    String partNo;
    String foto;

    int seekValue;

    myThread my;

    boolean IsRunning=true;
    int seekMax=100, seekMin=0;

    class myThread extends Thread{
        @Override
        public void run() {
            while (true)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(getActivity() == null)
                    return;

                getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               if (mp != null){
                                   seekBar.setProgress(mp.getCurrentPosition());
                                               sec.setText(formatTime( mp.getCurrentPosition()));

                                   }

                            }
                        });

                    }
            }

        }


    public PlayerFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        partNo =sharedPreferences.getString("partNo","bulunamadı");
        partUrl= sharedPreferences.getString("partUrl", "bulunamadı");
        foto = sharedPreferences.getString("foto","bulunamadı");
        mp = new MediaPlayer();



        try {
            mp.setDataSource(partUrl);
            mp.prepare();
         } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player, container, false);

        playButton =view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        partName= view.findViewById(R.id.partAdi);
        sec= view.findViewById(R.id.deneme);
        secTotal= view.findViewById(R.id.toplamSaniyesi);
        bookImage =view.findViewById(R.id.kitapKapak);


        seekBar = view.findViewById(R.id.seekBar);

        int second,minutes;
        minutes= (mp.getDuration()/1000)/60;
        second= (mp.getDuration()/1000) %60;
        if (second>9){
         secTotal.setText(""+ minutes + ":" +second);}
         else{secTotal.setText(""+ minutes + ":0" +second);}


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekValue = progress;
                  //  d.setText(seekValue);
                Log.e("erkanmal","" + seekValue);
                sec.setText(formatTime(seekValue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            //    sec.setText(seekValue*mp.getDuration());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mp.seekTo(seekValue);
            }
        });

        Picasso.get().load(foto).into(bookImage);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                seekBar.setMax(mp.getDuration());
/*
                try {    mixpanel.track("Çalınan Ses: "+ partUrl);
                    JSONObject props = new JSONObject();
                    props.put( "Android Ses", "ses calındı");
                    mixpanel.track("Ses",props);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
*/
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mp.pause();
            }
        });
        my= new myThread();
        my.start();

        return view;
    }



    @Override
    public void onPause() {
        Log.d("cagirildim", "onpause");
        super.onPause();
       // mp=null;
        my.interrupt();
        Log.e("cagirildim", "onpause");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @SuppressLint("DefaultLocale")
    public static String formatTime(int millis) {
        if (millis < 0)
            return "∞";

        int hour, min;
        hour = min = 0;
        millis /= 1000;

        if (millis >= 60) {
            min = millis / 60;
            millis %= 60;
        }

        if (min >= 60) {
            hour = min / 60;
            min %= 60;
        }

        String result = "";
        if (hour > 0)
            result += String.format("%02d:", hour);
        result += String.format("%02d:%02d", min, millis);

        return result;
    }
}
