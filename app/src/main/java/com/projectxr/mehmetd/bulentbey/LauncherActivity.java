package com.projectxr.mehmetd.bulentbey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;
import com.projectxr.mehmetd.bulentbey.Fragments.GirisFragment;
import com.projectxr.mehmetd.bulentbey.Fragments.KayitFragment;
import com.squareup.picasso.Picasso;


public class LauncherActivity extends AppCompatActivity {


    public VideoView baseVideo;
    ImageView imageView;

    Uri uri;
    public void stopVideo(){
        baseVideo.stopPlayback();
    }
    SharedPreferences sharedPreferences;

    public static String MIXPANEL_TOKEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(false)
                .init();

imageView=findViewById(R.id.bgimage);
Picasso.get().load(R.drawable.bgardiyanoglu).into(imageView);


        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                if (registrationId != null) {
                }
            }
        });
        MIXPANEL_TOKEN = "e2748ec0752b9f135c25ed0ddfd4e5bd";
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN);


        sharedPreferences = getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        String kullanici =sharedPreferences.getString("oauth_key", "kullanıcı");

        if (!kullanici.equals("kullanıcı"))
        {
            startActivity(new Intent(LauncherActivity.this,BaseActivity.class));

        }



        baseVideo = findViewById(R.id.videoView);

        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);



    }

    @Override
    protected void onResume() {
        super.onResume();
       try {
            baseVideo.setVideoURI(uri);
            baseVideo.start();

            baseVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);

                }
            });
        }catch (Exception e){}

    }



    public void girisYap(View view) {
        View b = findViewById(R.id.girisYap);
        View b2 = findViewById(R.id.kayitOl);
        View image = findViewById(R.id.bgimage);
        image.setVisibility(View.GONE);
        b.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        Fragment fragment = null;
        fragment = new GirisFragment();
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {

        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container2, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void kayitButton(View view) {
        View b = findViewById(R.id.girisYap);
        View b2 = findViewById(R.id.kayitOl);
        b.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        View image = findViewById(R.id.bgimage);
        image.setVisibility(View.GONE);
        Fragment fragment = null;
        fragment = new KayitFragment();
        loadFragment(fragment);

    }
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        if (!stateChanges.getFrom().getSubscribed() &&
                stateChanges.getTo().getSubscribed()) {
            new AlertDialog.Builder(this)
                    .setMessage("Bildirimlere başarı ile kayıt olundu!")
                    .show();
            // get player ID TODO:     playerID = stateChanges.getTo().getUserId();
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            finish();
            startActivity(getIntent());
        }

        return false;
        // Disable back button..............
    }

}
