package com.projectxr.mehmetd.bulentbey.API;



import com.projectxr.mehmetd.bulentbey.Models.BuyResponse;
import com.projectxr.mehmetd.bulentbey.Models.KitaplarResponse;
import com.projectxr.mehmetd.bulentbey.Models.LoginResponse;
import com.projectxr.mehmetd.bulentbey.Models.PartResponse;
import com.projectxr.mehmetd.bulentbey.Models.ProfileResponse;
import com.projectxr.mehmetd.bulentbey.Models.RegisterResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("users/register")
    Call<RegisterResponse> Register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("name") String name,
            @Field("surname") String surname,
            @Header("Content-Type") String contentType
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password

    );


    @GET("kitap/get_kitap")
    Call<KitaplarResponse> kitaplar(
            @Header("Authorization") String Authorization
    );

    @FormUrlEncoded
    @POST("kitap/get_kitaplar")
    Call<PartResponse> partGetir(
            @Field("id") String id,
            @Header("Authorization") String Authorization
    );


    @GET("users/profile")
    Call<ProfileResponse> profilCall(
            @Header("Authorization") String Authorization
    );

    @GET("users/premium")
    Call <BuyResponse> checkCall(
            @Header("Authorization") String Authorization
    );



}
