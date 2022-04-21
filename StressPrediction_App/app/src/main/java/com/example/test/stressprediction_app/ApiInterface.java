package com.example.test.stressprediction_app;


import com.example.test.stressprediction_app.Model.Assessment;
import com.example.test.stressprediction_app.Model.ResponseLogin;
import com.example.test.stressprediction_app.Model.ResponseMessage;
import com.example.test.stressprediction_app.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //String Url="http://192.168.0.101/stress_service/Service1.svc/";
    String Url="http://demoproject.in/stress_service/Service1.svc/";

    @POST("registerUser")
    @Headers({"Content-Type: application/json"})
    Call<ResponseMessage> registerUser(@Body User user);

    @GET("login/{EmailID}/{Password}")
    @Headers({"Content-Type: application/json"})
    Call<ResponseLogin> login(@Path("EmailID") String EmailID,
                              @Path("Password") String Password);

    @GET("getQuestions")
    @Headers({"Content-Type: application/json"})
    Call<List<Assessment>> getQuestions();


    @GET("getUserProfile/{UserID}")
    @Headers({"Content-Type: application/json"})
    Call<List<User>> getUserProfile(@Path("UserID") String UserID);

    @GET("forgotPassword/{EmailID}/")
    @Headers({"Content-Type: application/json"})
    Call<ResponseMessage> forgotPassword(@Path("EmailID") String EmailID);

}
