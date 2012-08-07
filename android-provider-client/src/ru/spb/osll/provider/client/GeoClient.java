package ru.spb.osll.provider.client;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import static ru.spb.osll.provider.client.GeoProviderStubs.*;

public class GeoClient extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        fillData();
    }
    
    private void fillData() {
        final TextView textView = (TextView) findViewById(R.id.text_container);

        String[] columns = new String[] { 
                COLUMN_DESCRIPTION, 
                COLUMN_LAT,
                COLUMN_LON
            };
        final ContentResolver resolver = getContentResolver();
        //resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        Cursor cur = resolver.query(CONTENT_URI, columns, null, null, null);
        
        while(cur.moveToNext()) {
            String description = cur.getString(cur.getColumnIndex(COLUMN_DESCRIPTION));
            float lat = cur.getFloat(cur.getColumnIndex(COLUMN_LAT));
            float lon = cur.getFloat(cur.getColumnIndex(COLUMN_LON));
            
            String mark = "Mark: " + description + ", lat: " + lat + ", lon: " + lon; 
            textView.append("\n" + mark);
        }
    }
}