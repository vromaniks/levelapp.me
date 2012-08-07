
package ru.spb.osll.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    // Database
    public static String DB_NAME = "MARKS_DB";
    public static int DB_VERSION = 1;
    
    // Database table
    public static final String TABLE_MARKS = "marks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " 
            + TABLE_MARKS
            + "(" 
            + COLUMN_ID + " integer primary key autoincrement, " 
            + COLUMN_DESCRIPTION + " text not null, " 
            + COLUMN_LAT + " float not null, "
            + COLUMN_LON + " float not null "
            + ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        fillData(db, 30.12f, 60.21f);
        fillData(db, 30.31f, 60.41f);
        fillData(db, 30.55f, 60.25f);
    }
    
    private void fillData(SQLiteDatabase db, float lon, float lat) {
        ContentValues val = new ContentValues();
        val.put(COLUMN_DESCRIPTION, "TBD");
        val.put(COLUMN_LON, lon);
        val.put(COLUMN_LAT, lat);
        db.insert(TABLE_MARKS, null, val);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(getClass().getSimpleName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKS);
        onCreate(db);
    }

}
