package com.example.report_0408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetParing extends AppCompatActivity {
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_paring);
        Intent intent1 = getIntent();
        String json = intent1.getStringExtra("json");
        Button button = findViewById(R.id.parsing_get);
        ListView listView = findViewById(R.id.listview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = new ArrayList<>();
                try {
                    JSONObject root = new JSONObject(json);
                    JSONArray array = root.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++){
                        User user = new User();
                        JSONObject object = array.getJSONObject(i);
                        String id = object.getString((String) object.names().get(0));
                        String email = object.getString((String) object.names().get(1));
                        String name = object.getString((String) object.names().get(2)) + object.getString((String) object.names().get(3));
                        String image = object.getString((String) object.names().get(4));
                        user.setId(id);
                        user.setEmail(email);
                        user.setName(name);
                        user.setImage(image);
                        users.add(user);
                    }
                } catch (JSONException e) {
                    Toast.makeText(GetParing.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                CustomAdapter adapter = new CustomAdapter(GetParing.this, users);
                listView.setAdapter(adapter);
            }
        });
    }
}