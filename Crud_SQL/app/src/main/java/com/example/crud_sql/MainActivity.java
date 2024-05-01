package com.example.crud_sql;

import static com.example.crud_sql.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nrp,nama;
    private Button simpan, ambildata, ubahdata, hapusdata;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper Opendb;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        nrp = (EditText) findViewById(R.id.nrp);
        nama = (EditText) findViewById(R.id.nama);
        simpan = (Button) findViewById(R.id.Simpan);
        ambildata = (Button) findViewById(R.id.ambildata);
        ubahdata = (Button) findViewById(R.id.UbahData);
        hapusdata = (Button) findViewById(R.id.HapusData);
        hapusdata.setOnClickListener(operasi);
        simpan.setOnClickListener(operasi);
        ambildata.setOnClickListener(operasi);
        ubahdata.setOnClickListener(operasi);

        Opendb = new SQLiteOpenHelper(this, "db.sql", null,1) {
            @Override
            public void onCreate(SQLiteDatabase db) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        dbku = Opendb.getWritableDatabase();
        dbku.execSQL("create table if not exists mhs(nrp TEXT, nama TEXT);");
    }

    @Override
    protected void onStop(){
        dbku.close();
        Opendb.close();
        super.onStop();}

    View.OnClickListener operasi = new View.OnClickListener() {
        @Override    public void onClick(View v) {
            if (v.getId() == R.id.Simpan) {
                simpan();
            } else if (v.getId() == R.id.ambildata) {
                ambildata();
            } else if (v.getId() == R.id.UbahData){
                update();
            } else if (v.getId() == R.id.HapusData){
                delete();
            }
        }
    };

    private void simpan()
    {
        ContentValues dataku = new ContentValues();
        dataku.put("nrp",nrp.getText().toString());
        dataku.put("nama",nama.getText().toString());
        dbku.insert("mhs",null,dataku);
        Toast.makeText(this,"Data Tersimpan", Toast.LENGTH_LONG).show();
    }

    private void ambildata(){
        Cursor cur = dbku.rawQuery("select * from mhs where nrp='" +
                nrp.getText().toString()+ "'",null);
        if(cur != null && cur.getCount() > 0) {
            Toast.makeText(this,"Data Ditemukan Sejumlah " +
                    cur.getCount(),Toast.LENGTH_LONG).show();
            cur.moveToFirst();
            int namaColumnIndex = cur.getColumnIndex("nama");
            if (namaColumnIndex != -1) {
                String namaValue = cur.getString(namaColumnIndex);
                nama.setText(namaValue != null ? namaValue : ""); // Set empty string if value is null
            } else {
                Toast.makeText(this, "Column 'nama' not found in the result set", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
        }
    }

    private void update(){
        ContentValues dataku = new ContentValues();
        dataku.put("nrp",nrp.getText().toString());
        dataku.put("nama",nama.getText().toString());
        dbku.update("mhs",dataku,"nrp='"+nrp.getText().toString()+"'",null);
        Toast.makeText(this,"Data Terupdate",Toast.LENGTH_LONG).show();
    }

    private void delete(){
        dbku.delete("mhs","nrp='"+nrp.getText().toString()+"'",null);
        Toast.makeText(this,"Data Terhapus",Toast.LENGTH_LONG).show();
    }


}
