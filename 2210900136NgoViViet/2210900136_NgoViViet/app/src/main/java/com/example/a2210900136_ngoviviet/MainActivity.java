package com.example.a2210900055_buihuuphuc;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;  // Đối tượng quản lý cơ sở dữ liệu SQLite
    ListView listViewSinhVien;  // ListView hiển thị danh sách sinh viên
    Button btnThem;  // Button thêm dữ liệu mẫu
    List<SinhVien> sinhVienList;  // Danh sách sinh viên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các View từ layout
        listViewSinhVien = findViewById(R.id.listViewSinhVien);
        btnThem = findViewById(R.id.btnThem);

        // Khởi tạo đối tượng DatabaseHelper và danh sách sinh viên
        db = new DatabaseHelper(this);
        sinhVienList = new ArrayList<>();

        // Tải dữ liệu từ cơ sở dữ liệu lên ListView
        loadData();

        // Sự kiện khi nhấn nút "Thêm dữ liệu mẫu"
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSampleData();  // Thêm dữ liệu mẫu
                loadData();  // Hiển thị lại dữ liệu
            }
        });
    }

    // Phương thức thêm dữ liệu mẫu vào cơ sở dữ liệu
    private void addSampleData() {
        db.insertData("SV01", "Nguyễn Văn A", "A");
        db.insertData("SV02", "Trần Thị B", "B");
        db.insertData("SV03", "Lê Văn C", "C");
        db.insertData("SV04", "Hoàng Thị D", "A+");
        db.insertData("SV05", "Phạm Văn E", "B+");
        Toast.makeText(this, "Thêm dữ liệu mẫu thành công!", Toast.LENGTH_SHORT).show();
    }

    // Phương thức loadData() - hiển thị dữ liệu từ SQLite lên ListView
    private void loadData() {
        sinhVienList.clear();  // Xóa danh sách cũ để làm mới
        Cursor cursor = db.getAllData();  // Lấy dữ liệu từ bảng SinhVien

        // Kiểm tra nếu không có dữ liệu
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Chưa có dữ liệu!", Toast.LENGTH_SHORT).show();
        } else {
            // Duyệt qua từng bản ghi trong Cursor và thêm vào danh sách sinh viên
            while (cursor.moveToNext()) {
                SinhVien sinhVien = new SinhVien(
                        cursor.getString(0),  // MaSV
                        cursor.getString(1),  // HoTen
                        cursor.getString(2)   // DiemChu
                );
                sinhVienList.add(sinhVien);
            }

            // Tạo adapter và gắn vào ListView để hiển thị danh sách
            SinhVienAdapter adapter = new SinhVienAdapter(this, R.layout.item_sinhvien, sinhVienList);
            listViewSinhVien.setAdapter(adapter);
        }

        cursor.close();  // Đóng con trỏ để giải phóng tài nguyên
    }
}
