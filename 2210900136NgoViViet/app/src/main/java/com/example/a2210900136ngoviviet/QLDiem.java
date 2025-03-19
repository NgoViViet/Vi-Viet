package com.example.a2210900136ngoviviet;

public class QLDiem {
}
package com.example.qldiem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Tên Database và Bảng
    private static final String DATABASE_NAME = "QLDiem.db";
    private static final String TABLE_NAME = "SinhVien";

    // Các cột trong bảng
    private static final String COL_1 = "MaSinhVien";
    private static final String COL_2 = "HoTen";
    private static final String COL_3 = "DiemSo";
    private static final String COL_4 = "DiemChu";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng SinhVien
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " TEXT PRIMARY KEY, " +
                COL_2 + " TEXT, " +
                COL_3 + " REAL, " +
                COL_4 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Hàm thêm sinh viên
    public boolean insertData(String maSinhVien, String hoTen, double diemSo, String diemChu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, maSinhVien);
        contentValues.put(COL_2, hoTen);
        contentValues.put(COL_3, diemSo);
        contentValues.put(COL_4, diemChu);
        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1; // Trả về true nếu thêm thành công
    }

    // Hàm lấy tất cả dữ liệu
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Hàm cập nhật dữ liệu
    public boolean updateData(String maSinhVien, String hoTen, double diemSo, String diemChu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, hoTen);
        contentValues.put(COL_3, diemSo);
        contentValues.put(COL_4, diemChu);

        int result = db.update(TABLE_NAME, contentValues, "MaSinhVien = ?", new String[]{maSinhVien});
        return result > 0; // Trả về true nếu cập nhật thành công
    }

    // Hàm xóa sinh viên
    public boolean deleteData(String maSinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "MaSinhVien = ?", new String[]{maSinhVien});
        return result > 0; // Trả về true nếu xóa thành công
    }
}

