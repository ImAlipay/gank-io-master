package com.gank.io.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * OperaDatabase:操作数据库
 *
 * @author zpl
 * @date 2018/6/4
 */
public class OperaDatabase {

    private final DatabaseHelper dbHelper;

    public OperaDatabase(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 增
     *
     * @param data
     */
    public void insert(NotificationData data) {
        String sql = "insert into " + DatabaseHelper.TABLE_NAME;

        sql += "(" + DatabaseHelper.FIELD_TIMSTAMP
                + ", " + DatabaseHelper.FIELD_DATATYPE
                + ", " + DatabaseHelper.FIELD_TRADENO
                + ", " + DatabaseHelper.FIELD_CONTENT
                + ")values(?, ?, ?, ?)";

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(sql, new String[]{
                data.getTimestamp() + "", data.getDataType(),
                data.getTradeNo(), data.getContent()});
        sqlite.close();
    }

    /**
     * 删
     *
     * @param id
     */
    public void delete(int id) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("delete from " + DatabaseHelper.TABLE_NAME + " where _id=?");
        sqlite.execSQL(sql, new Integer[]{id});
        sqlite.close();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<NotificationData> queryAll() {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<NotificationData> data = null;
        data = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("select * from " + DatabaseHelper.TABLE_NAME, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            NotificationData notificationData = new NotificationData();
            notificationData.setId(cursor.getInt(0));
            notificationData.setTimestamp(cursor.getString(1));
            notificationData.setDataType(cursor.getString(2));
            notificationData.setTradeNo(cursor.getString(3));
            notificationData.setContent(cursor.getString(4));
            data.add(notificationData);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return data;
    }

    /**
     * 分页查询(倒序)
     *
     * @return 通知集合
     */
    public List<NotificationData> queryLimit(String key, int pagesize, int startindex) {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<NotificationData> data = null;
        data = new ArrayList<>();
        Cursor cursor = sqlite.query(DatabaseHelper.TABLE_NAME, new String[]{}, DatabaseHelper.FIELD_DATATYPE + "=?", new String[]{key},
                null, null, "id desc",
                startindex + "," + pagesize);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            NotificationData notificationData = new NotificationData();
            notificationData.setId(cursor.getInt(0));
            notificationData.setTimestamp(cursor.getString(1));
            notificationData.setDataType(cursor.getString(2));
            notificationData.setTradeNo(cursor.getString(3));
            notificationData.setContent(cursor.getString(4));
            data.add(notificationData);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return data;
    }

    /**
     * 查
     *
     * @param where 根据dataType区分
     * @return
     */
    public List<NotificationData> query(String where) {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<NotificationData> data = null;
        data = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where " + DatabaseHelper.FIELD_DATATYPE + " = ?", new String[]{where});
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            NotificationData notificationData = new NotificationData();
            notificationData.setId(cursor.getInt(0));
            notificationData.setTimestamp(cursor.getString(1));
            notificationData.setDataType(cursor.getString(2));
            notificationData.setTradeNo(cursor.getString(3));
            notificationData.setContent(cursor.getString(4));
            data.add(notificationData);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();
        return data;
    }

    /**
     * 重置
     *
     * @param datas
     */
    public void reset(List<NotificationData> datas) {
        if (datas != null) {
            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
            // 删除全部
            sqlite.execSQL("delete from " + DatabaseHelper.TABLE_NAME);
            // 重新添加
            for (NotificationData data : datas) {
                insert(data);
            }
            sqlite.close();
        }
    }


    public void destory() {
        dbHelper.close();
    }
}
