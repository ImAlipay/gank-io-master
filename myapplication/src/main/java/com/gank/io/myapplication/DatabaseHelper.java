package com.gank.io.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper:创建数据库子类
 *
 * @author zpl
 * @date 2018/6/4
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "kbz_pay";
    /**
     * 不同用户需要不同的表格
     */
//    public static final String TABLE_NAME = "notification" + SPUtil.getMSISDN();
    public static final String TABLE_NAME = "notification";
    public static final String FIELD_TIMSTAMP = "Timestamp";
    /**
     * 类型
     * "1","trade"
     * "2","system"
     * "3","ad"
     */
    public static final String FIELD_DATATYPE = "DataType";
    public static final String FIELD_TRADENO = "TradeNo";
    public static final String FIELD_CONTENT = "Content";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "
                + TABLE_NAME
                + "(id integer primary key autoincrement,"
                + FIELD_TIMSTAMP + " varchar(64),"
                + FIELD_DATATYPE + " varchar(64),"
                + FIELD_TRADENO + " varchar(64),"
                + FIELD_CONTENT + " varchar(255))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库
    }
}