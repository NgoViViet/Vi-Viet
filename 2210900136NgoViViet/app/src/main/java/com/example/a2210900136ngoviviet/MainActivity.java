package com.example.a2210900136ngoviviet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
package com.example.qldiem;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo database
        databaseHelper = new DatabaseHelper(this);

        // Thêm dữ liệu mẫu
        boolean isInserted = databaseHelper.insertData("SV01", "Nguyen Lan Anh", 7.5, "B");
        if (isInserted) {
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
        }

        // Lấy dữ liệu và hiển thị
        Cursor cursor = databaseHelper.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Không có dữ liệu!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String maSinhVien = cursor.getString(0);
                String hoTen = cursor.getString(1);
                double diemSo = cursor.getDouble(2);
                String diemChu = cursor.getString(3);

                // Hiển thị dữ liệu trong Log hoặc Toast
                Toast.makeText(this, maSinhVien + " - " + hoTen + " - " + diemSo + " - " + diemChu, Toast.LENGTH_LONG).show();
            }
        }
    }
}
