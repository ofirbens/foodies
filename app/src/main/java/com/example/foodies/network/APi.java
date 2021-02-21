package com.example.foodies.network;
import com.example.foodies.Recipe;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface APi {

    @GET("AIzaSyD3Wv8mX97cNuL0C82siFXAK-8IQYNSgcE")
    Call<ArrayList<Recipe>> getRecipe(@Query("api_key") String apiKey);
  //  @GET("maps/api/getcode/json?")
    //Call<JsonObject> getLocationInfo(@Query("adress") String zipcode,@Query("sensor")boolean sensor);
//&address=7777&sensor false;

    //@Path(".")
  //  Call<ArrayList<Recipe>>
}