package com.example.daftargunuungjawatimur;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<DataGunung> dataGunung;
    private Context context;
    TextView txt_nama, txt_alamat;
    LinearLayout linear;
    String tID, tNama, tAlamat;

    public ListAdapter(List<DataGunung> dataGunung, Context context) {
        this.dataGunung = dataGunung;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataGunung.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.costum_list, null);
        txt_nama = v.findViewById(R.id.text_nama);
        txt_alamat = v.findViewById(R.id.text_alamat);
        linear = v.findViewById(R.id.linear);

        txt_nama.setText(dataGunung.get(position).getNAMA_GUNUNG());
        txt_alamat.setText(dataGunung.get(position).getALAMAT());

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InputActivity.class);
                intent.putExtra("ID", dataGunung.get(position).getID());
                intent.putExtra("nama_gunung", dataGunung.get(position).getNAMA_GUNUNG());
                intent.putExtra("alamat", dataGunung.get(position).getALAMAT());
                intent.putExtra("TANDA", "Ubah");

                context.startActivity(intent);
            }
        });
        return v;
    }
}
