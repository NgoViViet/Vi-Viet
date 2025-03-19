package com.example.a2210900136_ngoviviet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    private int resource;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.sinhVienList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        // Ánh xạ các thành phần trong item_sinhvien.xml
        TextView tvMaSV = convertView.findViewById(R.id.tvMaSV);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvDiemChu = convertView.findViewById(R.id.tvDiemChu);

        // Gán dữ liệu
        SinhVien sinhVien = sinhVienList.get(position);
        tvMaSV.setText("Mã SV: " + sinhVien.getMaSV());
        tvHoTen.setText("Họ Tên: " + sinhVien.getHoTen());
        tvDiemChu.setText("Điểm Chữ: " + sinhVien.getDiemChu());

        return convertView;
    }
}
