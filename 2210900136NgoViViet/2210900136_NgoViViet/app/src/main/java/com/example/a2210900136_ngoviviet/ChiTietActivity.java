package com.example.a2210900136_ngoviviet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietActivity extends AppCompatActivity {

    EditText etMaSV, etHoTen, etDiemChu;  // Các EditText nhập thông tin sinh viên
    Button btnLuu, btnHuy;  // Các Button lưu và hủy
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);

        // Ánh xạ các View từ layout
        etMaSV = findViewById(R.id.etMaSV);  // Mã sinh viên
        etHoTen = findViewById(R.id.etHoTen);  // Họ tên sinh viên
        etDiemChu = findViewById(R.id.etDiemChu);  // Điểm chữ
        btnLuu = findViewById(R.id.btnLuu);  // Nút Lưu
        btnHuy = findViewById(R.id.btnHuy);  // Nút Hủy

        db = new DatabaseHelper(this);  // Khởi tạo DatabaseHelper

        // Lấy dữ liệu từ Intent khi bấm vào sinh viên trong ListView
        Intent intent = getIntent();
        String maSV = intent.getStringExtra("MaSV");
        if (maSV != null) {
            // Nếu có mã sinh viên, hiển thị thông tin chi tiết sinh viên
            loadSinhVien(maSV);
        } else {
            // Nếu không có mã sinh viên, nghĩa là thêm mới
            etMaSV.setText("SV" + System.currentTimeMillis());  // Tạo mã ngẫu nhiên
        }

        // Xử lý sự kiện nút "Lưu"
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSinhVien();
            }
        });

        // Xử lý sự kiện nút "Hủy"
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Trở về màn hình chính
            }
        });
    }

    // Phương thức hiển thị thông tin chi tiết sinh viên
    private void loadSinhVien(String maSV) {
        SinhVien sv = db.getSinhVienByMaSV(maSV);  // Lấy sinh viên theo mã
        if (sv != null) {
            etMaSV.setText(sv.getMaSV());
            etHoTen.setText(sv.getHoTen());
            etDiemChu.setText(sv.getDiemChu());
        }
    }

    // Phương thức lưu sinh viên vào SQLite
    private void saveSinhVien() {
        String maSV = etMaSV.getText().toString();
        String hoTen = etHoTen.getText().toString();
        String diemChu = etDiemChu.getText().toString();

        if (hoTen.isEmpty() || diemChu.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = db.insertData(maSV, hoTen, diemChu);
        if (isInserted) {
            Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
            finish();  // Quay về màn hình chính sau khi lưu thành công
        } else {
            Toast.makeText(this, "Lưu thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}
