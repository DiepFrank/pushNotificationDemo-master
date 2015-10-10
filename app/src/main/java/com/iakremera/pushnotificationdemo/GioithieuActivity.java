package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class GioithieuActivity extends Activity {
    ListView lv;
    ArrayList<teacher> list_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu);
        lv = (ListView) findViewById(R.id.list_teacher);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        list_teacher = new ArrayList<teacher>();
        list_teacher.add(new teacher("Jose Mourinho", "Football", "26.01.1995", "ChelseaFC", R.drawable.mourinho,"0989177619"));
        CustomAdapter adapter = new CustomAdapter(this,R.layout.info_customview,list_teacher);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent data = new Intent(GioithieuActivity.this,ThongtinGiaovienActivity.class);
                data.putExtra("name",list_teacher.get(i).name);
                data.putExtra("birthday",list_teacher.get(i).birthday);
                data.putExtra("company",list_teacher.get(i).company);
                data.putExtra("phone",list_teacher.get(i).phone);
                data.putExtra("subject",list_teacher.get(i).subject);
                data.putExtra("avatar",list_teacher.get(i).avatar);
                startActivity(data);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gioithieu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.info: {
                Intent intent = new Intent(GioithieuActivity.this, GioithieuActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.thongbao: {
                Intent intent = new Intent(GioithieuActivity.this, ThongbaoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.showclass: {
                Intent intent = new Intent(GioithieuActivity.this, ShowClass.class);
                startActivity(intent);
                break;
            }
            case R.id.add:
            {
                Intent intent = new Intent(GioithieuActivity.this, ThemLopHoc.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }
}