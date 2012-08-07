
package ru.spb.osll.provider.client;

import android.net.Uri;

public class GeoProviderStubs {
    private GeoProviderStubs(){};

    public static final String TABLE_MARKS = "marks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";

    private static final String AUTHORITY = "ru.spb.osll.marks.provider";
    private static final String BASE_PATH = "marks";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
}
