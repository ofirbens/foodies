package com.example.foodies;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class OpenPage extends AppCompatActivity  {
    Button btnstart;
    private ImageView up,down;
    ImageButton user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_page);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        btnstart = findViewById(R.id.btnstart);
        //user = findViewById(R.id.user);
        //btnstart.setOnClickListener(this);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        up.setAnimation(animation);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //user.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
                //details= (LoginFragment)getFragmentManager().findFragmentById(R.id.fragment_login)
              //  getFragmentManager().beginTransaction().add(R.id.content_frame, ).commit();

            //}


        //});
    }




}







