package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Danh sách dữ liệu
        String[] data = {"Lập trình Android", "Lập trình Java", "Lập trình Python"};

        // Ánh xạ ListView
        ListView listView = findViewById(R.id.listView);

        // Tạo Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        // Gán Adapter cho ListView
        listView.setAdapter(adapter);
    }
}
