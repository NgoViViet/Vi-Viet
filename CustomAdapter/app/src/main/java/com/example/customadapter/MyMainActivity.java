package com.example.customadapter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyMainActivity extends AppCompatActivity {
    ArrayList<objItem> arrayList;
    MyAddapter adapter;
    ListView listView;
    Button btThoat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_main_actyvity_layout);

        // Thêm dữ liệu vào danh sách
        arrayList = new ArrayList<>();
        arrayList.add(new objItem("Banh ngot",R.drawable.anh01,150));
        arrayList.add(new objItem("Anh 2",R.drawable.a03,1950));
        arrayList.add(new objItem("Anh 3",R.drawable.anh011,1960));

        adapter = new MyAddapter(MyMainActivity.this,R.layout.item_layout,arrayList);

        listView = findViewById(R.id.my_main_list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, "Ban chon menu", Toast.LENGTH_SHORT).show();
        Show_Dialog_Add();
        return super.onOptionsItemSelected(item);
    }
    private void Show_Dialog_Add(){
        Dialog dialog = new Dialog(MyMainActivity.this);
        dialog.setContentView(R.layout.my_dialog_add);
        dialog.show();
        btThoat = dialog.findViewById(R.id.btCancel);
        btThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onDestroy();
                dialog.cancel();
            }
        });
        Button btThem = dialog.findViewById(R.id.btAdd);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItem();
            }
        });


    }

    private void AddItem(){
        Toast.makeText(this, "Thêm phân tử vào Array", Toast.LENGTH_SHORT).show();
    }
    public void DeleItem(){
        Toast.makeText(this, "Xóa item", Toast.LENGTH_SHORT).show();
    }
    public void UpdateItem(){
        Toast.makeText(this, "Update Item", Toast.LENGTH_SHORT).show();
    }
}
