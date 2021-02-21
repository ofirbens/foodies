package com.example.foodies;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private boolean desertIsChecked = false;
    private boolean mainIsChecked = false;
    private boolean startIsChecked = false;

    ArrayList<String> ingredientsArr;

    public static void setImageUrlDb(String imageUrlDb) {
        AddRecipeActivity.imageUrlDb = imageUrlDb;
    }

    private static String imageUrlDb = null;

    EditText recipeName;

    CheckBox desertCheckBox;
    CheckBox mainCheckBox;
    CheckBox startCheckBox;

    EditText ingredientsEditText;
    Switch igr_btn;
    TableLayout ingredientsEditTable;

    EditText instructionEditText;

    ImageView imageView;

    ImageButton add_Btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        recipeName = findViewById(R.id.recipe_name);

        desertCheckBox = findViewById(R.id.desertCB);
        mainCheckBox = findViewById(R.id.mealCB);
        startCheckBox = findViewById(R.id.startCB);

        ingredientsEditText = findViewById(R.id.ingredients_editText);
        igr_btn = findViewById(R.id.igr_btn);
        ingredientsEditTable = findViewById(R.id.ingredients_tableLayout);

        instructionEditText = findViewById(R.id.instruction);

        imageView = findViewById(R.id.image);

       add_Btn = findViewById(R.id.add_btn);

        desertCheckBox.setOnCheckedChangeListener(this);
        mainCheckBox.setOnCheckedChangeListener(this);
        startCheckBox.setOnCheckedChangeListener(this);

        ingredientsEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsEditText.selectAll();
            }
        });

        add_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap json = null;
                if (imageUrlDb == null) {
                    Toast.makeText(view.getContext(), getString(R.string.please_choose_an_image), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    json = DatabaseFunctions.CreateDbObjByParams(recipeName.getText().toString(), "null", "123123", imageUrlDb, ingredientsArr, instructionEditText.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                DatabaseFunctions.pushToDb(json, desertIsChecked, mainIsChecked, startIsChecked);

            }
        });

        igr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableRow row = new TableRow(view.getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                TextView textView = new TextView(view.getContext());
                if (ingredientsEditText.getText().equals("") || ingredientsEditText.getText().equals(null)) {
                    Toast.makeText(view.getContext(), getString(R.string.ingredient_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText(ingredientsEditText.getText());
                row.addView(textView);
                ingredientsEditTable.addView(row);
                ingredientsEditText.selectAll();
                ingredientsArr.add(ingredientsEditText.getText().toString());
            }
        });

        recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeName.selectAll();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChoiceDialog();
            }
        });

        ingredientsArr = new ArrayList<>();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        switch (id) {
            case R.id.desertCB:
                if (b) {
                    desertIsChecked = true;
                    if (mainIsChecked || startIsChecked) {
                        mainCheckBox.setChecked(false);
                        startCheckBox.setChecked(false);
                        mainIsChecked = false;
                        startIsChecked = false;
                    }
                }
                break;
            case R.id.mealCB:
                if (b) {
                    mainIsChecked = true;
                    if (desertIsChecked || startIsChecked) {
                        desertCheckBox.setChecked(false);
                        startCheckBox.setChecked(false);
                        desertIsChecked = false;
                        startIsChecked = false;
                    }
                }
                break;
            case R.id.startCB:
                if (b) {
                    startIsChecked = true;
                    if (desertIsChecked || mainIsChecked) {
                        desertCheckBox.setChecked(false);
                        mainCheckBox.setChecked(false);
                        desertIsChecked = false;
                        mainIsChecked = false;
                    }
                    break;
                }
        }
    }

        private void displayChoiceDialog() {
            String choiceString[] = new String[] {"Gallery" ,"Camera"};
            AlertDialog.Builder dialog= new AlertDialog.Builder(this);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setTitle("Select image from");
            dialog.setItems(choiceString,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent;
                            if (which ==0) {
                                intent = new Intent(
                                        Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            } else {
                                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            }
                            startActivityForResult(
                                    Intent.createChooser(intent, "Select profile picture"), 12345);
                        }
                    }).show();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==12345)
                if(resultCode == RESULT_OK&&data!=null) {
                    Uri selectedImageUri = data.getData();
                    if(selectedImageUri!=null)  // image selected from gallary
                    {
                        String imagePath = getRealPathFromURI(this, selectedImageUri);
                        Glide.with(imageView.getContext()).load(selectedImageUri).into(imageView);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            DatabaseFunctions.uploadImageToDb(bitmap, recipeName.getText().toString() + ".jpg",this.getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else     // image captured from camera
                    {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        Glide.with(imageView.getContext()).load(bitmap).into(imageView);
                        DatabaseFunctions.uploadImageToDb(bitmap, recipeName.getText().toString() + ".jpg", this.getApplicationContext());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    Log.d("==>","Operation canceled!");
                }
        }

        /**
         * get actual path from uri
         * @param context context
         * @param contentUri  uri
         * @return actual path
         */
        public static String getRealPathFromURI(Context context, Uri contentUri) {
            Cursor cursor = null;
            try {
                String[] projection = { MediaStore.Images.Media.DATA };
                cursor = context.getContentResolver().query(contentUri,  projection, null, null, null);

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
    }

}
