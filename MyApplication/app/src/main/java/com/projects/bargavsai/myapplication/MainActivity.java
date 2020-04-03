package com.projects.bargavsai.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    //private static final String TAG = "TAG";

    static MyRecyclerViewAdapter adapter;

    static List<Tasks> items = new ArrayList<>();

    static List<Tasks> sub_items = new ArrayList<>();

    static MySqliteOpenHelper mySqliteOpenHelper;

    static EditText text1,text2,text3;

    static Tasks item;

    static int pos;

    static RecyclerView recyclerView;

    static Context context;

    private String task_ext = "SELECT * FROM task";

    //static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        //Log.d(TAG,"on_create_1");

        //items = mySqliteOpenHelper.getAlldata();
        Log.d(TAG,"on_create");

        int i = 0;

        context = this;

        mySqliteOpenHelper = new MySqliteOpenHelper(this);


        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this,items);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        adapter.clear();

        items = mySqliteOpenHelper.getAlldata();

        adapter = new MyRecyclerViewAdapter(this,items);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onItemClick(View view, int position) {

        item = adapter.getItem(position);

        pos = position;

        //Log.d(TAG,item.getTitle());
        //text1.setText(item.getTitle());

        Intent i = new Intent(MainActivity.this, subtasks.class);

        startActivity(i);

        //finish();

        Log.d(TAG,"closed");


        Toast.makeText(this, "You clicked " + adapter.getItem(position).getTitle() + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                Log.d(TAG, "Add a new task");

                Intent i = new Intent(MainActivity.this, popup.class);

                startActivity(i);

                return true;

            case R.id.day_view:
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("d/MM/YYYY");
                String today = df.format(c);
                Log.d(TAG,today);
                RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                adapter.clear();

                items = mySqliteOpenHelper.getData(today);

                adapter = new MyRecyclerViewAdapter(this,items);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
                return true;

            case R.id.home_view:
                RecyclerView recyclerView1 = findViewById(R.id.my_recycler_view);
                recyclerView1.setLayoutManager(new LinearLayoutManager(this));

                adapter.clear();

                items = mySqliteOpenHelper.getAlldata();

                adapter = new MyRecyclerViewAdapter(this,items);
                adapter.setClickListener(this);
                recyclerView1.setAdapter(adapter);
                MyRecyclerViewAdapter.life = false;
                
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static MainActivity instance;

    public static MainActivity getInstance(){
        return instance;
    }

    public int fun(int p){

        Tasks item = adapter.getItem(p);

        RecyclerView recyclerView1 = findViewById(R.id.my_recycler_view);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        adapter.clear();

        if(p==0){
            items = mySqliteOpenHelper.getAlldata();
            adapter = new MyRecyclerViewAdapter(this,items);
            adapter.setClickListener(this);
            recyclerView1.setAdapter(adapter);
            return p;
        }

        else {
            items = mySqliteOpenHelper.getData_title(item.getTitle());

            adapter = new MyRecyclerViewAdapter(this, items);
            adapter.setClickListener(this);
            recyclerView1.setAdapter(adapter);
            return p;
        }
    }




}

