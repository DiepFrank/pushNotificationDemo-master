package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
        list_teacher.add(new teacher(getString(R.string.tb),getString(R.string.toan),getString(R.string.ko),getString(R.string.coloa),R.drawable.tb,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.nh),getString(R.string.van),getString(R.string.ko),getString(R.string.ko),R.drawable.nh,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.tn),getString(R.string.ly),getString(R.string.ko),getString(R.string.dhcn),R.drawable.tn,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.th),getString(R.string.hoa),getString(R.string.ko),getString(R.string.ntt),R.drawable.th,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.han),getString(R.string.sinh),getString(R.string.ko),getString(R.string.ko),R.drawable.han,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.rose),getString(R.string.anh),getString(R.string.ko),getString(R.string.ko),R.drawable.rsz_rose,getString(R.string.ko)));
        list_teacher.add(new teacher(getString(R.string.covan),getString(R.string.ql),getString(R.string.ko),getString(R.string.ko),R.drawable.rsz_1covan,getString(R.string.ko)));

        CustomAdapter adapter = new CustomAdapter(this,R.layout.info_customview,list_teacher);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent data = new Intent(GioithieuActivity.this, ThongtinGiaovienActivity.class);
                data.putExtra("name", list_teacher.get(i).name);
                data.putExtra("birthday", list_teacher.get(i).birthday);
                data.putExtra("company", list_teacher.get(i).company);
                data.putExtra("phone", list_teacher.get(i).phone);
                data.putExtra("subject", list_teacher.get(i).subject);
                data.putExtra("avatar", list_teacher.get(i).avatar);
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
                Move move = new Move(GioithieuActivity.this,GioithieuActivity.class);
                move.execute();
                break;
            }
            case R.id.thongbao: {
                Move move = new Move(GioithieuActivity.this,ThongbaoActivity.class);
                move.execute();
                break;
            }
            case R.id.showclass: {
                Move move = new Move(GioithieuActivity.this,ShowClass.class);
                move.execute();
                break;
            }
            case R.id.add:
            {
                Move move = new Move(GioithieuActivity.this,ThemLopHoc.class);
                move.execute();
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }
}