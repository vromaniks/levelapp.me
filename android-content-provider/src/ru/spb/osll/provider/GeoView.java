package ru.spb.osll.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class GeoView extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        fillData();
    }
    
    
    private void fillData() {
        String[] columns = new String[] { 
                DBHelper.COLUMN_DESCRIPTION, 
                DBHelper.COLUMN_LAT,
                DBHelper.COLUMN_LON
            };
        
        final ContentResolver resolver = getContentResolver();
        //resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        Cursor cur = resolver.query(GeoProvider.CONTENT_URI, columns, null, null, null);
        
        while(cur.moveToNext()) {
            String description = cur.getString(cur.getColumnIndex(DBHelper.COLUMN_DESCRIPTION));
            float lat = cur.getFloat(cur.getColumnIndex(DBHelper.COLUMN_LAT));
            float lon = cur.getFloat(cur.getColumnIndex(DBHelper.COLUMN_LON));
            
            String mark = "Mark: " + description + ", lat: " + lat + ", lon: " + lon; 
            Log.d("JsonLog", mark);
        }
    }
}