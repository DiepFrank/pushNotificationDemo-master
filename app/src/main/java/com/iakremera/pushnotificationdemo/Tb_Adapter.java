package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Diep_Chelsea on 09/10/2015.
 */
public class Tb_Adapter extends ArrayAdapter<Thongbao> {
    private Activity activity;
    private int layoutId;
    private ArrayList<Thongbao> list_tb;

    public Tb_Adapter(Activity activity, int layoutId,ArrayList<Thongbao> objects) {
        super(activity,layoutId,objects);
        this.activity = activity;
        this.layoutId=layoutId;
        this.list_tb = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        convertView = inflater.inflate(layoutId,null);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        content.setText(list_tb.get(position).content);
        time.setText(list_tb.get(position).time);
        return convertView;
    }
}
