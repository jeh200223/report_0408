package com.example.report_0408;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SumJson {
    public ArrayList<Setts> setts(String s1, String s2) {
        ArrayList<Setts> _setts = new ArrayList<>();
        try {
            JSONObject sumobject = new JSONObject(s1);
            JSONObject sumobject2 = new JSONObject(s2);
            Setts setts = new Setts();
            String name = sumobject2.getString("name");
            String id = sumobject.getString("id");
            String createAt = (String) sumobject.getString("createdAt");
            String address = sumobject2.getString("address");
            setts.setId(id);
            setts.setCreateAt(createAt);
            setts.setName(name);
            setts.setAddress(address);
            _setts.add(setts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _setts;
    }
}
