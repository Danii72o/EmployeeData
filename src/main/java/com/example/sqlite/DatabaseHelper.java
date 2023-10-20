package com.example.sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "student.db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Grades";

    public static final String COL_0 = "name";

    public static final String COL_1 = "surname";

    public static final String COL_2 = "marks";

    public static final String COL_3 = "id";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_command = "Create TABLE "+TABLE_NAME+"("+COL_0+" text, "+COL_1+" text, "+COL_2+" text, "+COL_3+" text);";

        sqLiteDatabase.execSQL(create_command);
    }


    public void insertData(ContentValues values){

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public Cursor getAlldata(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select * from "+TABLE_NAME,null);

        return c;
    }


    public void updateData(String id,ContentValues values){

        SQLiteDatabase db = this.getWritableDatabase();

        if(db.update(TABLE_NAME,values,COL_3+"=?",new String[]{id})>0){
            Toast.makeText(context, "updation successfull", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "No rows present with the id given", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        if(db.delete(TABLE_NAME,COL_3+"=?",new String[]{id})>0){
            Toast.makeText(context, "Deletion successfull", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "No rows present with the id given", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}