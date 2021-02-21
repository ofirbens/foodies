package com.example.foodies;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity   {
    ImageButton start_btn;
    ImageButton mains_btn;
    ImageButton dessert_btn;
    ImageButton more_option;
    TextView tap_for_more;
    TextView text_starts;
    TextView text_meal;
    TextView text_dessert;
    RecipeAdapter adapter;
    ImageButton spiner_main;
    SearchView search_view;
    ListView lv1;
    RecyclerView recyclerView;
    ArrayList<Recipe> arraylist = new ArrayList<Recipe>();
    ArrayList<Recipe> sortedArraylist = new ArrayList<Recipe>();
    ArrayList<ArrayList<String>> ingredientArr = new ArrayList<ArrayList<String>>();
    ArrayList<String> names = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setVisibility(View.GONE);
        //search_view.setVisible(isVisible);
        text_starts = (TextView) findViewById(R.id.text_starts);
        text_meal = (TextView) findViewById(R.id.text_meal);
        tap_for_more = (TextView) findViewById(R.id.tap_for_more);
        text_dessert = (TextView) findViewById(R.id.text_dessert);
        start_btn = (ImageButton) findViewById(R.id.start_btn);
        mains_btn = (ImageButton) findViewById(R.id.mains_btn);
        dessert_btn = (ImageButton) findViewById(R.id.dessert_btn);
        more_option = (ImageButton) findViewById(R.id.more_option);
        //final SearchView searchView = (SearchView) item.getActionView();
        //item.setVisible(false);
        search_view.setVisibility(View.GONE);


        FirebaseDatabase database = null;
        DatabaseReference recipes = null;
        DatabaseReference desserts = null;
        DatabaseReference mains = null;
        DatabaseReference starters = null;

        if (Locale.getDefault().getDisplayLanguage().equals("English") || Locale.getDefault().getDisplayLanguage().toLowerCase().equals("english") || Locale.getDefault().getDisplayLanguage().toLowerCase().equals("en")) {
            database = FirebaseDatabase.getInstance();
            recipes = database.getReference("recipesEng");
            desserts = recipes.child("desserts");
            mains = recipes.child("mains");
             starters = recipes.child("starters");
        } else {
            database = FirebaseDatabase.getInstance();
            recipes = database.getReference("recipes");
            desserts = recipes.child("desserts");
            mains = recipes.child("mains");
            starters = recipes.child("starters");
        }


        Toast.makeText(MainActivity.this, getString(R.string.choose_recipe), Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this, getString(R.string.look_for_prodacts), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this, sortedArraylist);

        RecipeViewModel viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        viewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {

            @Override
            public void onChanged(@Nullable List<Recipe> recipeList) {
                adapter = new RecipeAdapter(MainActivity.this, recipeList);
                recyclerView.setAdapter(adapter);
            }
        });


        final DatabaseReference finalDesserts = desserts;
        dessert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessert_btn.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                mains_btn.setVisibility(View.GONE);
                text_meal.setVisibility(View.GONE);
                text_dessert.setVisibility(View.GONE);
                text_starts.setVisibility(View.GONE);

                finalDesserts.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Recipe recipe = snapshot.getValue(Recipe.class);
                                //adapter.setHasStableIds(true);
                                recyclerView.setAdapter(adapter);
                                arraylist.add(recipe);
                                sortedArraylist.add(recipe);
                                ingredientArr.add(recipe.getIngredients());
                                names.add(recipe.getRecipeName());

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }
        });


        final DatabaseReference finalStarters = starters;
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessert_btn.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                mains_btn.setVisibility(View.GONE);
                text_meal.setVisibility(View.GONE);
                text_dessert.setVisibility(View.GONE);
                text_starts.setVisibility(View.GONE);
                finalStarters.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Recipe recipe = snapshot.getValue(Recipe.class);
                                //adapter.setHasStableIds(true);
                                recyclerView.setAdapter(adapter);
                                sortedArraylist.add(recipe);
                                arraylist.add(recipe);
                                ingredientArr.add(recipe.getIngredients());
                                names.add(recipe.getRecipeName());

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }
        });


        final DatabaseReference finalMains = mains;
        mains_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessert_btn.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                mains_btn.setVisibility(View.GONE);
                text_meal.setVisibility(View.GONE);
                text_dessert.setVisibility(View.GONE);
                text_starts.setVisibility(View.GONE);
                finalMains.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Recipe recipe = snapshot.getValue(Recipe.class);
                                //adapter.setHasStableIds(true);
                                recyclerView.setAdapter(adapter);

                                sortedArraylist.add(recipe);
                                arraylist.add(recipe);
                                ingredientArr.add(recipe.getIngredients());
                                names.add(recipe.getRecipeName());

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        // Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }
        });


        more_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_for_more.setVisibility(View.GONE);
                more_option.setVisibility(View.GONE);
                dessert_btn.setVisibility(View.GONE);
                start_btn.setVisibility(View.GONE);
                mains_btn.setVisibility(View.GONE);
                text_meal.setVisibility(View.GONE);
                text_dessert.setVisibility(View.GONE);
                text_starts.setVisibility(View.GONE);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_holder, new Fragment_main());
                ft.commit();

            }
        });
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); //need firebase authentication instanceif (firebaseAuth.getCurrentUser() != null) {//conected
        if (firebaseAuth.getCurrentUser() != null) {

            search_view.setVisibility(View.VISIBLE);
        }



      search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
           public boolean onQueryTextSubmit(String query) {
               sortedArraylist.clear();
               for (int ii = 0 ; ii < ingredientArr.size(); ii ++) {
                   if (ingredientArr.get(ii).contains(query) || names.get(ii).contains(query)) {
                       recyclerView.setAdapter(adapter);
                       sortedArraylist.add(arraylist.get(ii));
                    }
                }
                adapter.notifyDataSetChanged();
               return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                sortedArraylist.clear();
                for (int ii = 0 ; ii < ingredientArr.size(); ii ++) {
                    if (ingredientArr.get(ii).contains(newText) || names.get(ii).contains(newText)) {
                        recyclerView.setAdapter(adapter);
                        sortedArraylist.add(arraylist.get(ii));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
       });


    }
}


/*

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference recipeRef = rootRef.child("recipe");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String mains = ds.child("mains").getValue(String.class);
                    String starters = ds.child("starters").getValue(String.class);
                    list.add(mains + " / " +  starters);
                    Log.d("TAG", mains + " / " +  starters);
                }
                ListView listView = (ListView) findViewById(R.id.lv1);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        recipeRef.addListenerForSingleValueEvent(eventListener);






    }















/*
               searchQueries = new String[]{"TextView", "ListView", "SearchView",
                "RatingBar", "ToolBar", "Button", "EditText", "ToggleButton",
                "ImageView","SlidingDrawer","Android"};

        //holdQueries = new String[20];
        list = (ListView) findViewById(R.id.list_view);
        for (String searchQuery:searchQueries) {
            SearchQuery searchQuery1 = new SearchQuery(searchQuery);
            // Binds all strings into an array
            arraylist.add(searchQuery1);
        }
        adapter = new RecipeDataAdapter(this, arraylist);
        list.setAdapter(adapter);
        editsearch = (SearchView) findViewById(R.id.search_view);
        save_view = (Button) findViewById(R.id.save_view);
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                adapter.filter(text);
                return false;
            }
        });


       save_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "save prodact for recipe", Toast.LENGTH_SHORT).show();

                //  SearchView searchView = findViewById(R.id.search_view);
                //CharSequence query = searchView.getQueryHint();
                //searchView.setInputType(InputType.TYPE_CLASS_TEXT);


             }
        });





    }
}

*/











/*
public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayList<String> dataArray;
  int i;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<>();
        list.add (this.getString(R.string.yes));
        list.add (this.getString(R.string.apple));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
               //@Override
              // public jhjhj{
                // if(list.contains(query){
            //for(int i=0; i<dataArray.size(); i++) {
             //   list.contains(query) = dataArray.get(i);
           // }


            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);

                }else{
                    Toast.makeText(MainActivity.this,getString(R.string.No_Match_found),Toast.LENGTH_LONG).show();
                }
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });



        }



    }


*/





















