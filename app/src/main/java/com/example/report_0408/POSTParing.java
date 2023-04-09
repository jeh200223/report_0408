package com.example.report_0408;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class POSTParing extends AppCompatActivity {
    String name = "";
    String address = "";
    final String url = "https://reqres.in/api/users";
    String netjson = "";
    ArrayList<Setts> setts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_parsing);
        Button button = findViewById(R.id.btn1);
        EditText editText = findViewById(R.id.edit1);
        EditText editText1 = findViewById(R.id.edit2);
        TextView textView = findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                address = editText1.getText().toString();
                Gson gson = new Gson();
                JsonObject object = new JsonObject();
                object.addProperty("name", name);
                object.addProperty("address", address);
                String json = gson.toJson(object);
                GsonPostParsingAsyncTask asyncTask = new GsonPostParsingAsyncTask();
                try {
                    netjson = asyncTask.execute(url).get();
                    SumJson sumJson= new SumJson();
                    setts = sumJson.setts(netjson, json);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Gson gson1 = new Gson();
                textView.setText(gson1.toJson(setts).concat("\n".concat("\n").concat("이름 : ") + name.concat("\n").concat("주소 : ") + address));
            }
        });
    }
}
