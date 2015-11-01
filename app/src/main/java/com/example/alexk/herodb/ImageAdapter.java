package com.example.alexk.herodb;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<Hero> {

    private final List<Hero> list;
    private final Activity context;

    public ImageAdapter(Activity context, List<Hero> list) {
        super(context, R.layout.activity_list_item, list);
        this.context = context;
        this.list = list;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_item, parent, false);


        TextView textViewName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvAboutInfo = (TextView) rowView.findViewById(R.id.tvAboutInfo);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.ivPhoto);
        TextView tvWorld = (TextView) rowView.findViewById(R.id.tvWorld);

        textViewName.setText(list.get(position).getName());

        tvAboutInfo.setText(list.get(position).getAboutInfo());
        tvWorld.setText(list.get(position).getWorld());
        Uri uritest = Uri.parse(list.get(position).getPhotoFile());

        imageView.setImageURI(null);
        imageView.setImageURI(uritest);
        return rowView;
    }

}