package ru.spb.osll.provider;

import static ru.spb.osll.provider.DBHelper.*;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class GeoProvider extends ContentProvider {

    private DBHelper database;

    // Used for the UriMacher
    private static final int MARKS = 10;
    private static final int MARK_ID = 20;

    private static final String AUTHORITY = "ru.spb.osll.marks.provider";

    private static final String BASE_PATH = "marks";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/todos";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/todo";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, MARKS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", MARK_ID);
    }

    @Override
    public boolean onCreate() {
        database = new DBHelper(getContext()); 
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {

        // Check projection
        checkColumns(projection);

        // QueryBuilder: Set the table
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_MARKS);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
        case MARKS:
            break;
        case MARK_ID:
            // Adding the ID to the original query
            queryBuilder.appendWhere(COLUMN_ID + "=" + uri.getLastPathSegment());
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase db = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
        case MARKS:
            id = db.insert(TABLE_MARKS, null, values);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
        case MARKS:
            rowsDeleted = db.delete(TABLE_MARKS, selection, selectionArgs);
            break;
        case MARK_ID:
            String id = uri.getLastPathSegment();
            if (selection != null) {
                rowsDeleted = db.delete(TABLE_MARKS, COLUMN_ID + "=" + id + " and " +
                        selection, selectionArgs);
            } else {
                rowsDeleted = db.delete(TABLE_MARKS, COLUMN_ID + "=" + id, null);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase db = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
        case MARKS:
            rowsUpdated = db.update(TABLE_MARKS, values, selection, selectionArgs);
            break;
        case MARK_ID:
            String id = uri.getLastPathSegment();
            if (selection == null || selection == "") {
                rowsUpdated = db.update(TABLE_MARKS, values,
                        COLUMN_ID + "=" + id, null);
            } else {
                rowsUpdated = db.update(TABLE_MARKS, values, COLUMN_ID + "=" + id 
                        + " and " + selection, selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
    
    @Override
    public String getType(Uri uri) {
        return null;
    }

    private void checkColumns(String[] projection) {
        final String[] available = { 
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_DESCRIPTION, 
                DBHelper.COLUMN_LON,
                DBHelper.COLUMN_LAT
        };
        
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // Check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
} 


