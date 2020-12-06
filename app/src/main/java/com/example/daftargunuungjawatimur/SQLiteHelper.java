package com.example.daftargunuungjawatimur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String NAMA_DATABASE = "db_data";
    private static final String NAMA_TABEL = "tb_gunung";

    private static final String Colum_1 = "ID";
    private static final String Colum_2 = "nama_gunung";
    private static final String Colum_3 = "alamat";

    public SQLiteHelper(@Nullable Context context) {
        super(context, NAMA_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NAMA_TABEL + " (" +
                Colum_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Colum_2 + " TEXT, " +
                Colum_3 + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAMA_TABEL);
    }

    public boolean insertData(String nama_gunung, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Colum_2, nama_gunung);
        value.put(Colum_3, alamat);
        long result = db.insert(NAMA_TABEL, null, value);
        return result != -1;
    }

    public Cursor getDataGunung() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + NAMA_TABEL, null);
    }

    public boolean updateData(String id, String nama_gunung, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Colum_1, id);
        value.put(Colum_2, nama_gunung);
        value.put(Colum_3, alamat);
        db.update(NAMA_TABEL, value, Colum_1 + " = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Colum_1, id);
        return db.delete(NAMA_TABEL, Colum_1 + " = ?", new String[]{id});
    }
}
