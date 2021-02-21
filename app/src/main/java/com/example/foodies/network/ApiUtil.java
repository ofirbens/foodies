package com.example.foodies.network;
import com.example.foodies.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



    public class ApiUtil {

        static String BASE_URL = "https://recipe-6aac8-default-rtdb.firebaseio.com/";

        static final Type RECIPE_ARRAY_LIST_CLASS_TYPE = (new ArrayList<Recipe>()).getClass();

        public static APi getRetrofitApi() {


            Gson gson = new GsonBuilder()
                    // we remove from the response some wrapper tags from our movies array
                    .registerTypeAdapter(RECIPE_ARRAY_LIST_CLASS_TYPE, new RecipeJsonDeserializer())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        /*
        Create an implementation of the API endpoints defined by the service interface.
        The relative path for a given method is obtained from an annotation on the method describing
        the request type. The built-in methods are GET, PUT, POST, PATCH, HEAD, DELETE and OPTIONS.
        You can use a custom HTTP method with @HTTP. For a dynamic URL, omit the path on the annotation
         and annotate the first parameter with @Url.

         Method parameters can be used to replace parts of the URL by annotating them with @Path.
         Replacement sections are denoted by an identifier surrounded by curly braces (e.g., "{foo}").
         To add items to the query string of a URL use @Query.
         */
            APi api = retrofit.create(APi.class);

            return api;

        }

    }
