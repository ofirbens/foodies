package com.example.foodies;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class DatabaseFunctions {



    public static HashMap CreateDbObjByParams(String recipeName, String imageLocation, String usernameId, String imageToken, ArrayList<String> ingredientsArr, String instructions) throws JSONException {
//        JSONObject json = new JSONObject();
        JsonObject json = new JsonObject();
        json.addProperty("imageLocation", imageLocation);
        json.addProperty("imageToken", imageToken);
//        JSONArray jsArray = new JSONArray(ingredientsArr);
        String arr = new Gson().toJson(ingredientsArr);
        json.addProperty("ingredients", arr);
        json.addProperty("instructions", instructions);
        json.addProperty("recipeName", recipeName);
        json.addProperty("usernameId", usernameId);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(json);


        HashMap<String, Object> js = new HashMap();
        js.put("imageLocation", imageLocation);
        js.put("imageToken", imageToken);
        js.put("ingredients", ingredientsArr);
        js.put("instructions", instructions);
        js.put("recipeName", recipeName);
        js.put("usernameId", usernameId);

        return js;
    }


    public static void pushToDb(HashMap json, boolean desert, boolean main, boolean start) {
        FirebaseDatabase database = null;
        DatabaseReference recipes = null;

        if (Locale.getDefault().getDisplayLanguage().equals("English") || Locale.getDefault().getDisplayLanguage().toLowerCase().equals("english") || Locale.getDefault().getDisplayLanguage().toLowerCase().equals("en")) {
            database = FirebaseDatabase.getInstance();
            recipes = database.getReference("recipesEng");
        } else {
            database = FirebaseDatabase.getInstance();
            recipes = database.getReference("recipes");
        }

        if (desert) {
            DatabaseReference desserts = recipes.child("desserts");
            desserts.setValue(json);
        }
        else if (start) {
            DatabaseReference starters = recipes.child("starters");
            starters.setValue(json);
        }
        else if (main){
            DatabaseReference mains = recipes.child("mains");

            mains.push().setValue(json);
        }
    }

    public static void uploadImageToDb(Bitmap bitmap, String imageName, final Context context) {
        String STORAGE_URL = "gs://recipe-6aac8.appspot.com";
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(STORAGE_URL);
        StorageReference mountainsRef = storageRef.child(imageName+".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                if(task.isSuccessful()) {
                    Uri uri = task.getResult();
                    String token = uri.toString();
                    AddRecipeActivity.setImageUrlDb(token);
                } else {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Uri uri = task.getResult();
                    String token = uri.toString();
                    AddRecipeActivity.setImageUrlDb(token);
                }
            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                System.out.println("Upload is " + progress + "% done");
//                int currentprogress = (int) progress;
//                progressBar.setProgress(currentprogress);
//
//            }
//        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
//                System.out.println("Upload is paused");
//            }
        });


    }
}
