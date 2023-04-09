package com.example.report_0408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String page = "https://reqres.in/api/users?page=2";
    String json = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("report_0408");
        Button button = findViewById(R.id.button1);
        Button button1 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONThread thread = new JSONThread(MainActivity.this, page);
                thread.start();
                try {
                    thread.join();
                    json = thread.getResuit();
                } catch (InterruptedException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Intent intent1 = new Intent(MainActivity.this, GetParing.class);
                intent1.putExtra("json", json);
                startActivity(intent1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, POSTParing.class);
                startActivity(intent1);
            }
        });
    }
}