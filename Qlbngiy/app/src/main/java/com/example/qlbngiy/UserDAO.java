package com.example.qlbngiy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * UserDAO cung cấp các phương thức cho đăng ký và đăng nhập.
 */
public class UserDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Truy vấn đăng ký: thêm người dùng vào bảng Users
    public boolean registerUser(String name, String email, String phone, String address, String password) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("address", address);
        values.put("password", password);

        long result = db.insert("Users", null, values);
        return result != -1;
    }

    // Truy vấn đăng nhập: kiểm tra email và password
    public boolean loginUser(String email, String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ? AND password = ?",
                new String[]{email, password});
        boolean isLoggedIn = (cursor.getCount() > 0);
        cursor.close();
        return isLoggedIn;
    }

    public void close() {
        if (db != null && db.isOpen())
            db.close();
    }
}
