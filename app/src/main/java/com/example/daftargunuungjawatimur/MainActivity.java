package com.example.daftargunuungjawatimur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab_add;
    private ListView list_gunung;
    public ListAdapter adapter;
    private ArrayList<DataGunung> dataGunungArrayList = new ArrayList<>();
    private SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_gunung = (ListView) findViewById(R.id.List_view);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        helper = new SQLiteHelper(this);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, InputActivity.class));
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });
        readData();
    }

    private void readData() {
        dataGunungArrayList.clear();
        Cursor result = helper.getDataGunung();
        while (result.moveToNext()) {
            String id = result.getString(0);
            String nama_gunung = result.getString(1);
            String alamat = result.getString(2);

            dataGunungArrayList.add(new DataGunung(
                    id,
                    nama_gunung,
                    alamat
            ));
        }

        adapter = new ListAdapter(dataGunungArrayList, MainActivity.this);
        list_gunung.setAdapter(adapter);
    }
}