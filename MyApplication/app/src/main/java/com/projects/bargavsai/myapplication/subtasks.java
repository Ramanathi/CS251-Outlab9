package com.projects.bargavsai.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;
import static com.projects.bargavsai.myapplication.MainActivity.items;
import static com.projects.bargavsai.myapplication.MainActivity.recyclerView;

public class subtasks extends Activity {

    EditText text1,text2,text3;

    Button btn_insert,btn_delete;

    private MySqliteOpenHelper mySqliteOpenHelper;

    private DatePicker datePicker;

    private Calendar calendar;

    private TextView dateView;

    private int year, month, day, i = 0;

    String a,b,c;

    MyRecyclerViewAdapter adapter = MainActivity.adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpopup);

        mySqliteOpenHelper = new MySqliteOpenHelper(this);

        text1 = findViewById(R.id.editText1);

        text2 = findViewById(R.id.editText2);

        text3 = findViewById(R.id.editText3);

        Tasks item = MainActivity.item;

        //Log.d(TAG,item.getTitle());

//        text1 = MainActivity.text1;
//        text2 = MainActivity.text2;
//        text3 = MainActivity.text3;
        a = item.getTitle();
        b = item.getDescr();
        c = item.getDd();
        text1.setText(item.getTitle());
        text2.setText(item.getDescr());
        text3.setText(item.getDd());



        //mySqliteOpenHelper.delete(item.getTitle(),item.getDescr(),item.getDd());

        dateView = findViewById(R.id.editText3);

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);

        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month+1, day);

        btn_insert = findViewById(R.id.btn_insert);

        btn_delete = findViewById(R.id.btn_delete);

        // ArrayList<String> animalNames = MainActivity.animalNames;

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1,s2,s3;

                mySqliteOpenHelper.delete(a,b,c);

                Tasks item1 = new Tasks(a,b,c);

                items.remove(item1);

                adapter.notifyDataSetChanged();

                Tasks item;

                int pos = MainActivity.pos;

                //Log.d(TAG,text1.getText().toString());

                s1 = text1.getText().toString();

                s2 = text2.getText().toString();

                s3 = text3.getText().toString();

                item = adapter.getItem(pos);

                item.setTitle(s1);
                item.setDescr(s2);
                item.setDd(s3);

                boolean isInserted = mySqliteOpenHelper.insertData_task(s1,s2,s3);

                Log.d(TAG,"inserted");
                if(isInserted){

                    Toast.makeText(subtasks.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    text1.getText().clear();
                    text2.getText().clear();
                    //text3.getText().clear();
                    adapter.removeItem(pos);
                    finish();
                }
                else{
                    Toast.makeText(subtasks.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                }

                //finish();

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = MainActivity.pos;
                Tasks item = adapter.getItem(pos);
                String a = item.getTitle();
                String b = item.getDescr();
                String c = item.getDd();
                adapter.removeItem(pos);

                mySqliteOpenHelper.delete(a,b,c);

                Log.d(TAG, "onClick:done");

                finish();
            }
        });
    }



    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
