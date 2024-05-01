package com.example.simple_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView hasilnya;
    private EditText txtinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasilnya = (TextView) findViewById(R.id.result);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(op);
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                tampil_input();
            }
        }
    };

    private void tampil_input() {
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder dialognya = new AlertDialog.Builder(this);
        dialognya.setView(inputnya);
        txtinput = (EditText) inputnya.findViewById(R.id.edittext);

        dialognya
                .setCancelable(false)
                .setPositiveButton("OK", oknya)
                .setNegativeButton("Batal", oknya);

        dialognya.show();
    }

    DialogInterface.OnClickListener oknya = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case -1:
                    hasilnya.setText(txtinput.getText().toString());
                    break;
                case -2:
                    break;
            }
        }
    };
}

