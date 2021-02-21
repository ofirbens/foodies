package com.example.foodies.network;

import android.util.Log;

import com.example.foodies.Recipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeJsonDeserializer implements JsonDeserializer  {



        private static String TAG =RecipeJsonDeserializer.class.getSimpleName();

        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ArrayList<Recipe>  recipe = null;
            ArrayList<com.example.foodies.Recipe> recipes =  recipe;
            try {
                JsonObject jsonObject = json.getAsJsonObject();
                JsonArray recipeJsonArray = jsonObject.getAsJsonArray("results");
                recipe = new ArrayList<>(recipeJsonArray.size());
                for (int i = 0; i < recipeJsonArray.size(); i++) {
                    // adding the converted wrapper to our container
                    Recipe dematerialized = context.deserialize(recipeJsonArray.get(i), Recipe.class);
                    recipes.add(dematerialized);
                }
            } catch (JsonParseException e) {
                Log.e(TAG, String.format("Could not deserializ recipes: %s", json.toString()));
            }
            return recipes;
        }
    }
