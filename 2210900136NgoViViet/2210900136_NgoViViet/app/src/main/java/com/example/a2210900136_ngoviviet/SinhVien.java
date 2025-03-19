package com.example.a2210900136_ngoviviet;

public class SinhVien {
    private String maSV;
    private String hoTen;
    private String diemChu;

    public SinhVien(String maSV, String hoTen, String diemChu) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diemChu = diemChu;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiemChu() {
        return diemChu;
    }
}
