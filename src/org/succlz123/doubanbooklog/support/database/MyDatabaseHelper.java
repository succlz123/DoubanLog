package org.succlz123.doubanbooklog.support.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.bean.BookInfo;
import org.succlz123.doubanbooklog.bean.DbAccount;

/**
 * Created by fashi on 2015/3/27.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static String sql = "create table info(token text primary key,douban_user_name text,douban_user_id integer,expires_in integer,refresh_token text);";

    private static MyDatabaseHelper instance;

    public static synchronized MyDatabaseHelper getInstance() {
        if (instance == null) {
            instance = new MyDatabaseHelper(DoubanApplication.getInstance().getApplicationContext(), "doubanbooklog.db", null, 1);
        }
        return instance;
    }

    private MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sql);

    }

    public void insert(DbAccount account) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("token", account.getAccess_token());
        cv.put("douban_user_name", account.getDouban_user_name());
        cv.put("douban_user_id", account.getDouban_user_id());
        cv.put("expires_in", account.getExpires_in());
        cv.put("refresh_token", account.getRefresh_token());
        db.replace("info", null, cv);
    }

    public void insert(BookInfo bookInfo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("token", bookInfo.getAlternate());

        db.replace("info", null, cv);
    }


    public DbAccount get() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query("info", null, null, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            DbAccount account = new DbAccount();
            account.setAccess_token(c.getString(0));
            account.setDouban_user_name(c.getString(1));
            account.setDouban_user_id(c.getInt(2));
            account.setExpires_in(c.getInt(3));
            account.setRefresh_token(c.getString(4));
            return account;
        } else {
            return null;
        }
    }
}
