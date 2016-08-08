package cc.soham.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button insert;
    Button query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = (Button) findViewById(R.id.activity_main_insert);
        query = (Button) findViewById(R.id.activity_main_query);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSomethingIntoDatabase();
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });


    }

    public void insertSomethingIntoDatabase() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyOpenHelper.User.name, "soham");
        contentValues.put(MyOpenHelper.User.email, "soham@soham.cc");

        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        sqLiteDatabase.insert(MyOpenHelper.Tables.USER, null, contentValues);
    }

    public void query() {
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(MyOpenHelper.Tables.USER, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
                String email = cursor.getString(cursor.getColumnIndex(MyOpenHelper.User.email));
                String name = cursor.getString(cursor.getColumnIndex(MyOpenHelper.User.name));
                Log.d("MainActivity", "Row: " + _id + ", " + name + ", " + email);
            } while (cursor.moveToNext());
        }
    }


}
