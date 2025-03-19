package com.example.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MyAddapter extends BaseAdapter {
    private List<objItem>  list;
    private MyMainActivity context;
    private int itemlayout;

    public MyAddapter(MyMainActivity context, int itemlayout,List<objItem> list) {
        this.list = list;
        this.context = context;
        this.itemlayout = itemlayout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(itemlayout,null);


        ImageView img = view.findViewById(R.id.ImgView);
        TextView name = view.findViewById(R.id.txtName);
        TextView price = view.findViewById(R.id.txtPrice);
        ImageView viewUpdate = view.findViewById(R.id.imgUpdate);
        ImageView viewDel = view.findViewById(R.id.imgDel);

        Toast.makeText(context, "Errro", Toast.LENGTH_SHORT).show();
        objItem objItem = list.get(position);
        img.setImageResource(objItem.getImg());
        name.setText(objItem.getName());
        price.setText("Price:" + objItem.getPrice());

        viewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Xử lý update", Toast.LENGTH_SHORT).show();
                context.UpdateItem();
            }
        });

        viewDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Xử lý xóa", Toast.LENGTH_SHORT).show();
                context.DeleItem();
            }
        });
        return view;
    }
}
