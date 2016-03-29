package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by namjinha on 2015-12-22.
 */
public class ContentProviderTest extends ContentProvider
{
    public static final String CP_AUTHORITY = "com.test.contentprovider";
    public static final Uri CONTENT_URI =
            Uri.parse("content://"+ CP_AUTHORITY + "/contactable");

    public static final String DB_NAME = "cptest.db";
    public static final String TABLE_NAME = "contactable";
    public static final int DB_VERSION = 1;

    private DBHandler mDbHandler = null;
    private SQLiteDatabase mDb = null;

    @Override
    public boolean onCreate()
    {
        mDbHandler = new DBHandler(getContext());
        mDb = mDbHandler.getWritableDatabase();
        return (mDb == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = null;
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(TABLE_NAME);
        cursor = sqlBuilder.query(mDb, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        long rowID = mDb.insert(TABLE_NAME, null, values);

        if (rowID > 0)
        {
            Uri notiuri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(notiuri, null);
            return notiuri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }
}
