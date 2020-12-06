package com.example.daftargunuungjawatimur;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InputActivity extends AppCompatActivity {
    private EditText create_nama, create_alamat;
    private FloatingActionButton fab_done, fab_delete;
    private SQLiteHelper helper;

    private String pilih = "Tambah";
    private String id_gunung, nama_gunung, alamat_gunung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        create_nama = findViewById(R.id.create_nama);
        create_alamat = findViewById(R.id.create_alamat);

        SQLiteHelper helper = new SQLiteHelper(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            getSupportActionBar().setTitle(("Ubah Data"));
            id_gunung = bundle.getString("ID");
            nama_gunung = bundle.getString("nama_gunung");
            alamat_gunung = bundle.getString("alamat");
            pilih = bundle.getString("TANDA");
            create_nama.setText(nama_gunung);
            create_alamat.setText(alamat_gunung);

        } else {
            getSupportActionBar().setTitle(("Tambah Data"));
        }
        fab_delete = (FloatingActionButton) findViewById(R.id.fab_delete);
        if (pilih.equals("Tambah")) {
            fab_delete.setVisibility(View.INVISIBLE);
        }else {
            fab_delete.setVisibility(View.VISIBLE);
        }
        fab_done = (FloatingActionButton) findViewById(R.id.fab_done);
        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = create_nama.getText().toString();
                String alamat = create_alamat.getText().toString();
                String id = id_gunung;

                if (TextUtils.isEmpty(nama)) {
                    create_nama.setError("Data tidak Boleh Kosong");
                    create_nama.requestFocus();
                } else if (TextUtils.isEmpty(alamat)) {
                    create_alamat.setError("Data tidak Boleh Kosong");
                    create_alamat.requestFocus();
                } else {
                    if (pilih.equals("Tambah")) {
                        boolean isInsert = helper.insertData(nama, alamat);
                        if (isInsert) {
                            Toast.makeText(InputActivity.this, "Data Berhasil dimasukan", Toast.LENGTH_SHORT).show();
                            clear();
                            startActivity(new Intent(InputActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(InputActivity.this, "Data Gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        boolean update = helper.updateData(
                                id,
                                nama,
                                alamat
                        );
                        if (update) {
                            Toast.makeText(InputActivity.this, "Data Berhasil di update", Toast.LENGTH_SHORT).show();
                            clear();
                            startActivity(new Intent(InputActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(InputActivity.this, "Data Gagal di update", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_gunung;
                Integer delete = helper.deleteData(
                        id
                );
                if (delete>0) {
                    Toast.makeText(InputActivity.this, "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
                    clear();
                    startActivity(new Intent(InputActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(InputActivity.this, "Data Gagal di hapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clear() {
        create_nama.setText(null);
        create_alamat.setText(null);
    }
}
