package cc.soham.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by sohammondal on 07/08/16.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public interface Tables {
        String USER = "user";
    }

    public interface User {
        String name = "name";
        String email = "email";
    }

    final String CREATE_USER_TABLE = "create table " + Tables.USER + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + User.name + " TEXT,"
            + User.email + " TEXT);";

    public static final String DATABASE_NAME = "vodafonedb";
    public static final int CURRENT_VERSION = 1;

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
