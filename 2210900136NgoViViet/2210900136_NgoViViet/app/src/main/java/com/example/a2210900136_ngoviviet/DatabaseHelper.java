package com.example.a2210900136_ngoviviet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên Database và Bảng
    private static final String DATABASE_NAME = "QLDiem_2210900131_NgoViViet.db";
    private static final String TABLE_NAME = "SinhVien";

    // Các cột trong bảng
    private static final String COL_1 = "MaSV";
    private static final String COL_2 = "HoTen";
    private static final String COL_3 = "DiemChu";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_1 + " TEXT PRIMARY KEY, "
                + COL_2 + " TEXT NOT NULL, "
                + COL_3 + " TEXT NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thêm dữ liệu vào bảng
    public boolean insertData(String maSV, String hoTen, String diemChu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, maSV);
        contentValues.put(COL_2, hoTen);
        contentValues.put(COL_3, diemChu);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Lấy dữ liệu
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
