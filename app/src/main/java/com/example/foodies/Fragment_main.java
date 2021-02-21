package com.example.foodies;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Fragment_main extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment, parent, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button login_frag = (Button) view.findViewById(R.id.login_frag);
        Button unlogin_frag = (Button) view.findViewById(R.id.unlogin_frag);
        unlogin_frag.setVisibility(View.INVISIBLE);
        Button register_frag = (Button) view.findViewById(R.id.register_frag);
        Button upload_recipe_frag = (Button) view.findViewById(R.id.upload_recipe_frag);
        ImageButton exit_to= (ImageButton )view.findViewById(R.id.exit_to);
        Button upload_recipe_frag1 = (Button) view.findViewById(R.id.upload_recipe_frag1);
       upload_recipe_frag1.setVisibility(View.INVISIBLE);
        Button search_frag = (Button) view.findViewById(R.id.search_frag);
        Button search_frag1 = (Button) view.findViewById(R.id.search_frag1);
       search_frag1.setVisibility(View.INVISIBLE);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); //need firebase authentication instanceif (firebaseAuth.getCurrentUser() != null) {//conected
        if (firebaseAuth.getCurrentUser() != null) {
            search_frag.setVisibility(View.INVISIBLE);
            unlogin_frag.setVisibility(View.VISIBLE);
            upload_recipe_frag.setVisibility(View.INVISIBLE);
            search_frag1.setVisibility(View.VISIBLE);
            upload_recipe_frag1.setVisibility(View.VISIBLE);
            login_frag.setVisibility(View.INVISIBLE);
        }



       exit_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().getFragmentManager().popBackStack();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });



        unlogin_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),getString(R.string.log_out),Toast.LENGTH_SHORT).show();
            }
        });

        login_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });

        register_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);


            }
        });

        upload_recipe_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),getString(R.string.just_for),Toast.LENGTH_SHORT).show();

                 Intent intent = new Intent(getActivity(),AddRecipeActivity.class);
                  startActivity(intent);


            }
        });

        upload_recipe_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),getString(R.string.just_for),Toast.LENGTH_SHORT).show();

                //   Intent intent = new Intent(getActivity(),AddRecipeActivity.class);
                // startActivity(intent);


            }
        });



        search_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),getString(R.string.allow),Toast.LENGTH_SHORT).show();

                  Intent intent = new Intent(getActivity(), MainActivity.class);
                   startActivity(intent);


            }
        });

        search_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),getString(R.string.just_for),Toast.LENGTH_SHORT).show();

                //  Intent intent = new Intent(getActivity(), LoginActivity.class);
                // startActivity(intent);


            }
        });






    }


}



/*

  login_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



 */