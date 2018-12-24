package com.projectxr.mehmetd.bulentbey;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.projectxr.mehmetd.bulentbey.Fragments.TabFragment11;
import com.projectxr.mehmetd.bulentbey.Fragments.TabFragment22;
import com.projectxr.mehmetd.bulentbey.Fragments.TabFragment33;
import com.projectxr.mehmetd.bulentbey.Fragments.tab_fragment4;


public class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    private VideoView baseVideo;
    Uri uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        baseVideo = findViewById(R.id.videoView2);

        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.mainvideo);


        loadFragment(new TabFragment11());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

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
                        mediaPlayer.setVolume(0f, 0f);
                    }
                });
            }catch (Exception e){}

        }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.profil:
                fragment = new TabFragment11();
                break;
            case R.id.dinleID:
                fragment = new TabFragment22();
                break;
            case R.id.iletisim:
                fragment = new TabFragment33();
                break;
            case R.id.profile:
                fragment = new tab_fragment4();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

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


}
