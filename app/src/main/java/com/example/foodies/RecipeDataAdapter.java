
package com.example.foodies;
/*
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeDataAdapter extends AppCompatActivity {

    private ListView lv1;
    private EditText Edit_text;
    private ImageButton find;
    private ImageButton delete;
    private Boolean searchMode = false;
    private Boolean itemSelected = false;
    private int selectedPosition = 0;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("todo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv1);
        Edit_text = (EditText) findViewById(R.id.Edit_text);
        find = (ImageButton) findViewById(R.id.find);
        delete = (ImageButton) findViewById(R.id.delete);
        delete.setEnabled(false);
        private void addChildEventListener() {
            ChildEventListener childListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    adapter.add(
                            (String) dataSnapshot.child("description").getValue());

                    listKeys.add(dataSnapshot.getKey());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    int index = listKeys.indexOf(key);

                    if (index != -1) {
                        listItems.remove(index);
                        listKeys.remove(index);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            dbRef.addChildEventListener(childListener);
        }











    }
}



*/














































































/*

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import java.util.List;
import java.util.Locale;
/*
    public class RecipeDataAdapter extends BaseAdapter {


        Context mContext;
        LayoutInflater inflater;
        private List<SearchQuery> searchQueries = null;
        private ArrayList<SearchQuery> arraylist;

        public RecipeDataAdapter(Context context, List<Recipe> searchQueries) {
            mContext = context;
            this.searchQueries = searchQueries;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<SearchQuery>();
            this.arraylist.addAll(searchQueries);
        }

        public class ViewHolder {
            TextView name;
        }

        @Override
        public int getCount() {
            return searchQueries.size();
        }

        @Override
        public SearchQuery getItem(int position) {
            return searchQueries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.list_items, null);
                // Locate the TextViews in listview_item.xml
                holder.name = (TextView) view.findViewById(R.id.name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            // Set the results into TextViews
            holder.name.setText(searchQueries.get(position).getQuery());
            return view;
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            searchQueries.clear();
            if (charText.length() == 0) {
                searchQueries.addAll(arraylist);
            } else {
                for (SearchQuery wp : arraylist) {
                    if (wp.getQuery().toLowerCase(Locale.getDefault()).contains(charText)) {
                        searchQueries.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }




*/











































   /* private List<Recipe> recipes;
    private myRecipeListener listener;
    private Object Context;

    public interface myRecipeListener {
        void onRecipeClicked(int position, View view);
        //void onrecipeClicked(int position, View view);

    }

    public void setListener(myRecipeListener listener) {
        this.listener = listener;
    }

    public RecipeDataAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
       // TextView kind;
        TextView name;
       // TextView time;
        //ImageView image_lisit;
        //ImageButton image_play;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            // kind = (TextView) itemView.findViewById(R.id.Kind);
          //  time = (TextView) itemView.findViewById(R.id.time);
            //image_play = (ImageButton) itemView.findViewById(R.id.image_play);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null) {
                        listener.onRecipeClicked(getAdapterPosition(), view);
                        //Intent intent = new Intent(MainActivity.this,Player_media.class);
                        //intent.putExtra("size",name.getText().toString());
                        //intent.putExtra("size",size1.getText().toString());
                        //startActivity(intent);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null)
                        listener.onRecipeClicked(getAdapterPosition(), view);
                    return false;
                }
            });


        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view, parent, false);
        RecipeViewHolder recipeViewHolder = new  RecipeViewHolder(view);
        return recipeViewHolder;

    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.name.setText(recipe.getName());
        //holder.kind.setText(player.getKind());
        //holder.image_lisit.setImageResource(player.getimage_id());
       // holder.time.setText(player.getTime());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}



*/


