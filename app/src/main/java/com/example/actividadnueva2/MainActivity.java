package com.example.actividadnueva2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDB MyDBA;
    private Cursor cur;
    final LinkedList<String> mWordList=new LinkedList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        Resources res = getResources();

        MyDatabaseHelper dbAss = new MyDatabaseHelper(this);
         MyDBA = new MyDB(this);

        cur = MyDBA.selectRecords();
        startManagingCursor(cur);
        String[] from = new String[]{"name"};
        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter adapta = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_dropdown_item,cur,from,to);
        ArrayAdapter<CharSequence> adapter;

        adapta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // final LinkedList<String> mWordList=new LinkedList<String>();

        for (int i = 0; i < cur.getCount(); i++) {
            mWordList.add(cur.getString(1)+ cur.getString(2) );
            cur.moveToNext();
        }




        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        WordListAdapter mAdapter = new WordListAdapter( this, mWordList,MyDBA);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Creardb(MyDB MyDBA){
        MyDBA.createRecords("0","Sun","274");
        MyDBA.createRecords("1","Jupiter","24.92");
        MyDBA.createRecords("2","Neptune","11.15");
        MyDBA.createRecords("3","Saturn","10.44");
        MyDBA.createRecords("4","Earth","9.798");
        MyDBA.createRecords("5","Uranus","8.87");
        MyDBA.createRecords("6","Venus","8.87");
        MyDBA.createRecords("7","Mars","3.71");
        MyDBA.createRecords("8","Mercury","3.7");
        MyDBA.createRecords("9","Moon","1.62");
        MyDBA.createRecords("10","Pluto","0.58");
            }

            public void dialog(){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Añadir una nueva constante");

// Set up the input
                final EditText input = new EditText(this);
                final EditText input2 = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                input2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                input.setHint("Titulo");
                layout.addView(input); // Notice this is an add method

// Add another TextView here for the "Description" label
                input2.setHint("Gravedad");
                layout.addView(input2); // Another add method

                // Again this is a set method, not add
                builder.setView(layout);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String  m_Text = input.getText().toString();
                      String  m_gravity= input2.getText().toString();
                        MyDBA.createRecords(String.valueOf(mWordList.size()+1),m_Text,m_gravity);
                        cur = MyDBA.selectRecords();
                        cur.moveToLast();
                        mWordList.add(cur.getString(1)+ cur.getString(2) );
                        cur.moveToNext();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }

    public void showFoodOrder() {
        Intent intent = new Intent(this,OrderActivity.class);

    }


}
