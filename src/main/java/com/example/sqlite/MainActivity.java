package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etSurname, etMarks, etId;
    private TextView tvShowData;


    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializations();
    }


    private void initializations() {
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etMarks = findViewById(R.id.et_marks);
        etId = findViewById(R.id.et_id);
        tvShowData = findViewById(R.id.tv_show_data);


        dbHelper = new DatabaseHelper(this);
    }




    public void addData(View view) {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String marks = etMarks.getText().toString();
        String id = etId.getText().toString();

        if (!(name.isEmpty() || surname.isEmpty() || marks.isEmpty() || id.isEmpty())) {

            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.COL_0, name);
            values.put(DatabaseHelper.COL_1, surname);
            values.put(DatabaseHelper.COL_2, marks);
            values.put(DatabaseHelper.COL_3, id);


            dbHelper.insertData(values);
            Toast.makeText(this, "VALUES INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please provide all fields data", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAll(View view) {


        Cursor c = dbHelper.getAlldata();

        if(c.moveToFirst()) {
            tvShowData.setText("Show Details\n");

            do {

                String name = c.getString(0);
                String surname = c.getString(1);
                String marks = c.getString(2);
                String id = c.getString(3);


                tvShowData.append("\nName: " + name);
                tvShowData.append("\nSurname: " + surname);
                tvShowData.append("\nMarks: " + marks);
                tvShowData.append("\nId: " + id);
                tvShowData.append("\n\n");


            } while (c.moveToNext());
        }

        else{
            Toast.makeText(this, "Table is empty!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String marks = etMarks.getText().toString();
        String id = etId.getText().toString();

        if (!(name.isEmpty() || surname.isEmpty() || marks.isEmpty() || id.isEmpty())) {
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.COL_0, name);
            values.put(DatabaseHelper.COL_1, surname);
            values.put(DatabaseHelper.COL_2, marks);


            dbHelper.updateData(id, values);

        }else{
            Toast.makeText(this, "Please provide all fields data", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {

        String id = etId.getText().toString();


        dbHelper.deleteData(id);
    }
}