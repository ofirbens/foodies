package com.example.foodies;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Recipe {
    public Recipe(){}
    @SerializedName("usernameId")
    private String usernameId;

    @SerializedName("recipeName")
    private String recipeName;

    @SerializedName("imageLocation")
    private String imageLocation;

    @SerializedName("imageToken")
    private String imageToken;

    @SerializedName("ingredients")
    private ArrayList<String> ingredients;

    @SerializedName("instructions")
    private String instructions;


    public Recipe(String usernameId, String imageToken, String instructions, String recipeName, String imageLocation, ArrayList<String> ingredients) {
        this.usernameId = usernameId;
        this.imageToken = imageToken;
        this.instructions = instructions;
        this.recipeName = recipeName;
        this.imageLocation = imageLocation;
        this.ingredients = ingredients;
    }

    public String getUsernameId() {
        return usernameId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getImageToken() {
        return imageToken;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}


