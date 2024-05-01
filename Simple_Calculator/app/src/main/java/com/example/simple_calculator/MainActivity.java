package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText; // Add this line
import android.widget.Toast;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    // Declare EditText and TextView variables
    private EditText editBil1, editBil2;
    private TextView textHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText and TextView variables
        editBil1 = findViewById(R.id.EditText1);
        editBil2 = findViewById(R.id.EditText2);
        textHasil = findViewById(R.id.Hasil);
    }

    // Method to handle button clicks
    public void operasi(View view) {
        float bil1, bil2, hasil = 0;
        Button bt = (Button) view;

        // Parsing input from EditText fields
        bil1 = Float.parseFloat(editBil1.getText().toString());
        bil2 = Float.parseFloat(editBil2.getText().toString());

        // Performing calculations based on the clicked button
        if (view.getId() == R.id.btnTambah) {
            hasil = bil1 + bil2;
        } else if (view.getId() == R.id.btnKurang) {
            hasil = bil1 - bil2;
        } else if (view.getId() == R.id.btnKali) {
            hasil = bil1 * bil2;
        } else if (view.getId() == R.id.btnBagi) {
            if (bil2 != 0) { // Check for division by zero
                hasil = bil1 / bil2;
            } else {
                // Handle division by zero error
                Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                return; // Exit operasi method
            }
        }

        // Displaying the result in TextView
        textHasil.setText(bil1 + " " + bt.getText() + " " + bil2 + " = " + hasil);
    }
}
