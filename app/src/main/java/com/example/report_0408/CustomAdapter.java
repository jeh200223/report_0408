package com.example.report_0408;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> users;

    public CustomAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, R.layout.activity_detail, users);
        this.context = context;
        this.users = users;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        User user = getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_detail, null);
        holder.image = convertView.findViewById(R.id.image);
        holder.id = convertView.findViewById(R.id.id);
        holder.name = convertView.findViewById(R.id.name);
        holder.email = convertView.findViewById(R.id.email);
        holder.id.setText("ID : " + user.getId());
        holder.name.setText("이름 : " + user.getName());
        holder.email.setText("email : " + user.getEmail());
        ImageThread thread = new ImageThread(context, user.getImage());
        thread.start();
        try {
            thread.join();
            holder.image.setImageBitmap(thread.getBitmap());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView id;
        TextView name;
        TextView email;
    }
}
