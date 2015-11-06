package com.example.alexk.herodb;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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
    static class ViewHolder {
        TextView textViewName;
        TextView tvAboutInfo;
        ImageView heroAvatar;
        TextView tvWorld;
    }







    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list_item, parent, false);
            holder = new ViewHolder();
            holder.textViewName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvAboutInfo = (TextView) convertView.findViewById(R.id.tvAboutInfo);
            holder.heroAvatar = (ImageView) convertView.findViewById(R.id.ivPhoto);
            holder.tvWorld = (TextView) convertView.findViewById(R.id.tvWorld);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.textViewName.setText(list.get(position).getName());
        holder.tvAboutInfo.setText(list.get(position).getAboutInfo());
        holder.tvWorld.setText(list.get(position).getWorld());
        Uri uritest = Uri.parse(list.get(position).getPhotoFile());

        holder.heroAvatar.setImageURI(null);
        holder.heroAvatar.setImageURI(uritest);
        return convertView;

    }

}