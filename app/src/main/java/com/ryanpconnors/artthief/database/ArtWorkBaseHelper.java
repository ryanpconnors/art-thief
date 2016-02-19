package com.ryanpconnors.artthief.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ryanpconnors.artthief.database.ArtWorkDbSchema.*;


/**
 * Created by Ryan Connors on 2/12/16.
 */
public class ArtWorkBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "artWorkBase.db";

    public ArtWorkBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the Info Table
        db.execSQL("create table " + InfoTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            InfoTable.Cols.UUID + ", " +
            InfoTable.Cols.DATA_VERSION + ", " +
            InfoTable.Cols.SHOW_YEAR + ", " +
            InfoTable.Cols.DATE_LAST_UPDATED +
            ")"
        );

        // Create the ArtWork Table
        db.execSQL("create table " + ArtWorkTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            ArtWorkTable.Cols.UUID + ", " +
            ArtWorkTable.Cols.ART_THIEF_ID + "," +
            ArtWorkTable.Cols.SHOW_ID + ", " +
            ArtWorkTable.Cols.TITLE + ", " +
            ArtWorkTable.Cols.ARTIST + ", " +
            ArtWorkTable.Cols.MEDIA + ", " +
            ArtWorkTable.Cols.TAGS + ", " +
            ArtWorkTable.Cols.SMALL_IMAGE_URL + ", " +
            ArtWorkTable.Cols.SMALL_IMAGE_PATH + ", " +
            ArtWorkTable.Cols.SMALL_IMAGE_BLOB + "," +
            ArtWorkTable.Cols.LARGE_IMAGE_URL + ", " +
            ArtWorkTable.Cols.LARGE_IMAGE_PATH + ", " +
            ArtWorkTable.Cols.LARGE_IMAGE_BLOB + "," +
            ArtWorkTable.Cols.STARS + ", " +
            ArtWorkTable.Cols.TAKEN +
            ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
