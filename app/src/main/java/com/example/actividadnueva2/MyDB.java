package com.example.actividadnueva2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB{

    private MyDatabaseHelper dbHelper;  

    private SQLiteDatabase database;

    public final static String EMP_TABLE="Gravity"; // name of table

    public final static String PLN_ID="_id";
    public final static String PLN_NAME ="name";
    public final static String PLN_Gravity="gravity";

    /** 
     * 
     * @param context 
     */  
    public MyDB(Context context){
        dbHelper = new MyDatabaseHelper(context);  
        database = dbHelper.getWritableDatabase();

    }


    public long createRecords(String id, String name,String gravity){
        ContentValues values = new ContentValues();
        values.put(PLN_ID, id);
        values.put(PLN_NAME, name);
        values.put(PLN_Gravity, gravity);
        return database.insert(EMP_TABLE, null, values);  
    }    

    public Cursor selectRecords() {
        String[] cols = new String[] {PLN_ID, PLN_NAME,PLN_Gravity};
        Cursor mCursor = database.query(true, EMP_TABLE,cols,null  
            , null, null, null, null, null);  
        if (mCursor != null) {  
            mCursor.moveToFirst();  
        }  
        return mCursor; // iterate to get each value.
    }

    public void deleteItem(String get_ID)
    {
        database.execSQL("DELETE FROM Gravity WHERE name='"+get_ID+"'");
    }

}