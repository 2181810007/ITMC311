package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.content.ContentValues;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editText2);
        editTextGrade = findViewById(R.id.editText3);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, editTextName.getText().toString());
        values.put(StudentsProvider.GRADE, editTextGrade.getText().toString());

        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

        Toast.makeText(this, "Added: " + uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        Uri students = StudentsProvider.CONTENT_URI;
        Cursor c = getContentResolver().query(students, null, null, null, StudentsProvider._ID);

        if (c != null && c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndexOrThrow(StudentsProvider._ID));
                String name = c.getString(c.getColumnIndexOrThrow(StudentsProvider.NAME));
                String grade = c.getString(c.getColumnIndexOrThrow(StudentsProvider.GRADE));
                Toast.makeText(this, id + ", " + name + ", " + grade, Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
            c.close();
        }
    }
}
