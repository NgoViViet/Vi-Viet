package com.example.customadapter;

public class objItem {
    private String Name;
    private int Img;
    private  double Price;

    public objItem(String name, int img, double price) {
        Name = name;
        Img = img;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
