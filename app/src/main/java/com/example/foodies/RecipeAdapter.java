package com.example.foodies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;



    public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
        Context mCtx;
        List<Recipe> RecipeList;

        final String RECIPE_IMAGE_URL = ".";

        public RecipeAdapter(Context mCtx, List<Recipe> recipeList) {
            this.mCtx = mCtx;
            this.RecipeList = recipeList;
        }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview, viewGroup, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
            Recipe recipe = RecipeList.get(position);

//            Glide.with(mCtx)
//                    .load(RECIPE_IMAGE_URL + recipe.getImageToken())
//                    .into(recipeViewHolder.imageView);

            Glide.with(mCtx).load(recipe.getImageToken()).into(recipeViewHolder.imageView);

//            recipeViewHolder.usernameId.setText(recipe.getUsernameId());
//            recipeViewHolder.imageLocation.setText(recipe.getImageLocation());

            String ingerd = recipe.getIngredients().toString();
            ingerd = ingerd.replace("[","");
            ingerd = ingerd.replace("]","");
            ingerd = mCtx.getResources().getString(R.string.ingredients) + "\n" +ingerd;
            recipeViewHolder.ingredients.setText(ingerd);
            recipeViewHolder.textView.setText(recipe.getRecipeName());
            String instu = mCtx.getResources().getString(R.string.instructions) + "\n" + recipe.getInstructions();
            recipeViewHolder.desc_textview.setText(instu);
        }

        @Override
        public int getItemCount() {
            return RecipeList.size();
        }

        class RecipeViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            TextView desc_textview;
            TextView usernameId ;
            TextView imageLocation;
            TextView ingredients;

            public RecipeViewHolder(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
                desc_textview = itemView.findViewById(R.id.description_textview);
//                usernameId = itemView.findViewById(R.id.usernameId);
//                imageLocation = itemView.findViewById(R.id.imageLocation);
                ingredients = itemView.findViewById(R.id.ingredients);
            }
        }
    }





