package com.projects.bargavsai.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.projects.bargavsai.myapplication.Contract.*;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import static android.content.ContentValues.TAG;

import static android.content.ContentValues.TAG;
import static com.projects.bargavsai.myapplication.MainActivity.items;
import static com.projects.bargavsai.myapplication.MainActivity.sub_items;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    private static final String database_name = "company.db";
    private static final int database_version = 1;

    MyRecyclerViewAdapter adapter = MainActivity.adapter;

    private static String descr = "description",title =  "title",date = "date",subtask = "subtask",id ="id";

    private String database_create = "CREATE TABLE task (title TEXT not null,description TEXT,date TEXT,PRIMARY KEY(title,description,date));";

    private String database_create_sub = "CREATE TABLE sub_task (title TEXT not null,description TEXT,date TEXT,task TEXT,PRIMARY KEY(title,description,date,task));";

    private String task_ext = "SELECT * FROM task";

    public MySqliteOpenHelper(Context context){

        super(context,database_name,null,database_version);
    }

    SQLiteDatabase db = this.getWritableDatabase();


    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(database_delete);
        db.execSQL(database_create);
        db.execSQL(database_create_sub);

        Log.d(TAG,"Database created");

        ContentValues contentValues = new ContentValues();

        contentValues.put("title","ZEN");
        contentValues.put("description","If you chase two rabbits, you catch none.");
        contentValues.put("date","");

        db.insert("task",null,contentValues);

        ContentValues c = new ContentValues();

        c.put("title","Acads");
        c.put("description","Padhai ki baatein");
        c.put("date","31/12/2019");

        db.insert("task",null,c);

        ContentValues c1 = new ContentValues();

        c1.put("title","Self improvement");
        c1.put("description","Reading list, blogs, exercise, etc.");
        c1.put("date","30/12/2019");

        db.insert("task",null,c1);

        ContentValues c2 = new ContentValues();

        c2.put("title","Research");
        c2.put("description","Pet projects");
        c2.put("date","");

        db.insert("task",null,c2);

        ContentValues c3 = new ContentValues();

        c3.put("title","Hobbies");
        c3.put("description","<3");
        c3.put("date","");

        db.insert("task",null,c3);

        ContentValues c4 = new ContentValues();

        c4.put("title","Exercise");
        c4.put("description","someday?");
        c4.put("date","27/02/2021");
        c4.put("task","Self improvement");

        db.insert("sub_task",null,c4);

        ContentValues c5 = new ContentValues();

        c5.put("title","Reading list");
        c5.put("description","My bucket list:\\nHear the Wind Sing\\nThe Fountainhead\\nAtlas Shrugged\\nA prisoner of birth");
        c5.put("date","");
        c5.put("task","Self improvement");

        db.insert("sub_task",null,c5);

        ContentValues c6 = new ContentValues();

        c6.put("title","Origami");
        c6.put("description","cranes and tigers");
        c6.put("date","29/10/2019");
        c6.put("task","Hobbies");

        db.insert("sub_task",null,c6);

        ContentValues c7 = new ContentValues();

        c7.put("title","Drum practice!");
        c7.put("description","Aim:\\nHallowed be thy name,\\nAcid Rain (LTE)");
        c7.put("date","29/10/2019");
        c7.put("task","Hobbies");

        db.insert("sub_task",null,c7);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // changes in the database

    }

    public boolean insertData_task(String tit, String description, String dd){

        Tasks item;
        //Tasks item = new Tasks(tit,descr,dd);

        ContentValues contentValues = new ContentValues();

        contentValues.put("title",tit);

        contentValues.put("description",description);

        contentValues.put("date", dd);

        long result = db.insert("task",null,contentValues);

        //Log.d(TAG,"ctvgg");

        if(result == -1)
            return false;
        else
            item = new Tasks(tit,description,dd);
            items.add(item);
            adapter.notifyDataSetChanged();

            Log.d(TAG,"insert_data");
            return true;

    }

    public boolean insertData_subtask(String tit, String description, String dd,String task){

        Tasks item;
        //Tasks item = new Tasks(tit,descr,dd);

        ContentValues contentValues = new ContentValues();

        contentValues.put("title",tit);

        contentValues.put("description",description);

        contentValues.put("date", dd);

        contentValues.put("task",task);

        long result = db.insert("sub_task",null,contentValues);

        //Log.d(TAG,"ctvgg");

        if(result == -1)
            return false;
        else
            item = new Tasks(tit,description,dd);
        sub_items.add(item);
        adapter.notifyDataSetChanged();

        Log.d(TAG,"insert_data");
        return true;

    }




    public boolean delete(String tit,String description,String dd){
        Tasks item;
        long result =  db.delete("task","title=? and description=? and date=?",new String[]{tit,description,dd});

        if(result == -1)
            return false;
        else
            //item = new Tasks(tit,description,dd);
            //items.remove(item);
            Log.d(TAG,"deleted");
            return true;
    }

    public List<Tasks> getData(String date){

        //String get_task = "task WHERE description = " + date;
        String[] columns = {"title","description","date"};

        Cursor cursor = db.query("task",columns,"date=?",new String[]{date},null,null,null);

        List<Tasks> tasks = new ArrayList<>();
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(MySqliteOpenHelper.title);
            int index1 = cursor.getColumnIndex(MySqliteOpenHelper.descr);
            int index2 = cursor.getColumnIndex(MySqliteOpenHelper.date);

            String tit = cursor.getString(index);
            String descr = cursor.getString(index1);
            String dd = cursor.getString(index2);

            Tasks some_tasks = new Tasks(tit,descr,dd);
            tasks.add(some_tasks);
            //tasks = new Tasks(tit,descr,dd);
        }

        return tasks;
    }

    public List<Tasks> getData_title(String title){

        //String get_task = "task WHERE description = " + date;
        String[] columns = {"title","description","date","task"};

        Cursor cursor = db.query("sub_task",columns,"task=?",new String[]{title},null,null,null);

        List<Tasks> tasks = new ArrayList<>();
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(MySqliteOpenHelper.title);
            int index1 = cursor.getColumnIndex(MySqliteOpenHelper.descr);
            int index2 = cursor.getColumnIndex(MySqliteOpenHelper.date);

            String tit = cursor.getString(index);
            String descr = cursor.getString(index1);
            String dd = cursor.getString(index2);

            Tasks some_tasks = new Tasks(tit,descr,dd);
            tasks.add(some_tasks);
            //tasks = new Tasks(tit,descr,dd);
        }

        return tasks;
    }

    public List<Tasks> getAlldata(){
        List<Tasks> list = new ArrayList<>();

        String get_all_tasks = "task";

        Cursor cursor = db.query(get_all_tasks,null,null,null,null,null,null);

        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(MySqliteOpenHelper.title);
            int index1 = cursor.getColumnIndex(MySqliteOpenHelper.descr);
            int index2 = cursor.getColumnIndex(MySqliteOpenHelper.date);

            String tit = cursor.getString(index);
            String descr = cursor.getString(index1);
            String dd = cursor.getString(index2);

            Tasks all_tasks = new Tasks(tit,descr,dd);
            list.add(all_tasks);
        }

        return list;

    }


}
