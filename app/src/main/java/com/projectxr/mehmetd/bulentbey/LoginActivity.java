package com.projectxr.mehmetd.bulentbey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.VideoView;

import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;
import com.projectxr.mehmetd.bulentbey.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private VideoView loginVideo;
    SharedPreferences sharedPreferences;

    EditText idd,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginVideo = findViewById(R.id.videoView);


        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);


        try {

            loginVideo.setVideoURI(uri);
            loginVideo.start();
            loginVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
        }catch (Exception e){}

        idd = findViewById(R.id.id);
        password=findViewById(R.id.password);

        sharedPreferences = this.getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);

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

    public void LoginMethod(View view) {
login();
        }

    public void registerMethod(View view) {
    }

    public void login() {
        String id = idd.getText().toString().trim();
        String password2=password.getText().toString().trim();

        RetrofitService retrofitService= RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
        Call<LoginResponse> call =retrofitService.login(id,password2);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                startActivity(new Intent(LoginActivity.this,BaseActivity.class));

                sharedPreferences.edit().putString("oauth_key", response.body().getKey().toString()).commit();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });




    }
}
