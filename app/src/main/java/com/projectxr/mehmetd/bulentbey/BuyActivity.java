package com.projectxr.mehmetd.bulentbey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.projectxr.mehmetd.bulentbey.API.RetrofitClient;
import com.projectxr.mehmetd.bulentbey.API.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BuyActivity extends AppCompatActivity  {
    BillingProcessor bp;

    private static final String ACTIVITY_NUMBER = "activity_num";
    private static final String LOG_TAG = "iabv3";

    private static final String PRODUCT_ID = "aylik_abonelik";
    private static final String SUBSCRIPTION_ID = "aylik_abonelik";
    private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmmyNzc8I8oQWn98ujHx6bDMvdH0RCPZMmpBndNUwbpvXDp3R10HKcGO43nI8PyWe6fq4I3JLIzWHuPqXTdm3/H3u+vG/2uGEo7VrAMsxYYqC4t491lYgriBUJ0X/MGzUtaorD+qR0YoCmoracv40dMm69pUKcnlOEQNE2QpM8tZQUQb7NoWvsfI1ykmJzjiNqaxhqgqzg2+w2tk3pl78aesq891Q7uMtX0/jdpBR6bMUovuAXxXJylqWlwfV4f8WceRWamOEooDvFjGdlkViYqTujy6RtOVmYlPJJj0KDw1mV1HJG+deuDkDe3GoEkqhnvV0/F7PSbv81MUxQhQ9PwIDAQAB"; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators
    private static final String MERCHANT_ID="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmmyNzc8I8oQWn98ujHx6bDMvdH0RCPZMmpBndNUwbpvXDp3R10HKcGO43nI8PyWe6fq4I3JLIzWHuPqXTdm3/H3u+vG/2uGEo7VrAMsxYYqC4t491lYgriBUJ0X/MGzUtaorD+qR0YoCmoracv40dMm69pUKcnlOEQNE2QpM8tZQUQb7NoWvsfI1ykmJzjiNqaxhqgqzg2+w2tk3pl78aesq891Q7uMtX0/jdpBR6bMUovuAXxXJylqWlwfV4f8WceRWamOEooDvFjGdlkViYqTujy6RtOVmYlPJJj0KDw1mV1HJG+deuDkDe3GoEkqhnvV0/F7PSbv81MUxQhQ9PwIDAQAB";

    private boolean readyToPurchase = true;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.projectxr.mehmetd", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_buy);
        bp = new BillingProcessor(this, LICENSE_KEY, MERCHANT_ID, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased( String productId, TransactionDetails details) {
                showToast("onProductPurchased: " + productId);

            }
            @Override
            public void onBillingError(int errorCode, Throwable error) {
                showToast("onBillingError: " + Integer.toString(errorCode));
            }
            @Override
            public void onBillingInitialized() {
                showToast("onBillingInitialized");
                readyToPurchase = true;

            }
            @Override
            public void onPurchaseHistoryRestored() {
                showToast("onPurchaseHistoryRestored");
                for(String sku : bp.listOwnedProducts())
                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                for(String sku : bp.listOwnedSubscriptions())
                    Log.d(LOG_TAG, "Owned Subscription: " + sku);

            }
        });
    }


    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.subscribeButton:
                bp.subscribe(this,SUBSCRIPTION_ID);
                checkSubsc();

                break;
            case R.id.updateSubscriptionsButton:
                if (bp.loadOwnedPurchasesFromGoogle()) {
                    showToast("Subscriptions updated.");
                    checkSubsc();

                }
                break;
            case R.id.subsDetailsButton:
                SkuDetails subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID);
                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
                break;

            default:
                break;
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void checkSubsc() {
        if (bp.isSubscribed(SUBSCRIPTION_ID)) {

            String auth = "bearer " + sharedPreferences.getString("oauth_key", "bulunamadı");
            RetrofitService retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService.class);

            Call call = retrofitService.checkCall(auth);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {

                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
        }


    }

}
