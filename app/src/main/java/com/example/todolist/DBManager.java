package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;


public class DBManager {
    String dbname = "TodoList";
    String dbTable = "List";
    String colId = "_id";
    String colTitle = "title";
    String colDescp = "description";
    String colTime = "time";
    int dbVersion = 1;


    //Creste table if not exists
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+ colId +" INTEGER PRIMARY KEY, "+
            colTitle + " TEXT, "+ colDescp +" TEXT, "+ colTime+ " TEXT);";

    SQLiteDatabase sqlDb =null;

    public DBManager(Context context){
        DatabaseHelperClass db = new DatabaseHelperClass(context);
        sqlDb = db.getWritableDatabase();
    }

    public class DatabaseHelperClass extends SQLiteOpenHelper{

        public DatabaseHelperClass(@Nullable Context context) {
                super(context, dbname, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sqlCreateTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS";
            db.execSQL(query+ dbTable);
        }
    }

    public long Insert(ContentValues contentValues){
        long id = sqlDb.insert(dbTable, "", contentValues);
        return id;
    }
    public Cursor Query(String[] projection, String selection, String[] selectionArgs, String sorOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(dbTable);
        Cursor cursor = qb.query(sqlDb, projection, selection, selectionArgs, null, null, sorOrder);
        return cursor;
    }
    public int Delete(String selection, String[] selectioArgs){
        int count = sqlDb.delete(dbTable, selection, selectioArgs);
        return count;
    }

    public int Update(ContentValues values, String selection, String[] selectionArgs){
        int count = sqlDb.update(dbTable, values, selection, selectionArgs);
        return count;
    }


}
