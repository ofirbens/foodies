package com.example.foodies;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodies.network.APi;
import com.example.foodies.network.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel extends ViewModel {


        //this is the data that we will fetch asynchronously
        private MutableLiveData<List<Recipe>> recipeList;

        //we will call this method to get the data
        public LiveData<List<Recipe>> getRecipe() {
            //if the list is null
            if (recipeList == null) {
                recipeList = new MutableLiveData<>();
                //we will load it asynchronously from server in this method
                loadRecipe();
            }
            //finally we will return the list
            return recipeList;
        }


        //This method is using Retrofit to get the JSON data from URL
        private void loadRecipe() {

            APi api = ApiUtil.getRetrofitApi();

            Call<ArrayList<Recipe>> call = api.getRecipe("AIzaSyD3Wv8mX97cNuL0C82siFXAK-8IQYNSgcE");
           // Call<ArrayList<Recipe>> call = api.getRecipe();api.getRecipe(path:"")
            call.enqueue(new Callback<ArrayList<Recipe>>() {
                @Override
                public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                    //finally we are setting the list to our MutableLiveData
                    recipeList.setValue(response.body());
                }

                @Override
                public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

                    Log.d("Error","");
                }
            });
        }
    }








