package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ThongtinGiaovienActivity extends Activity {
    TextView name,phone,birthday,company,subject;
    ImageView avt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_giaovien);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        Intent intent = getIntent();
        phone = (TextView) findViewById(R.id.phone);
        name = (TextView) findViewById(R.id.teacher_name);
        subject = (TextView) findViewById(R.id.teacher_subject);
        avt = (ImageView) findViewById(R.id.imageView);
        birthday = (TextView) findViewById(R.id.birthday);
        company = (TextView) findViewById(R.id.company);
        phone.setText(intent.getStringExtra("phone"));
        subject.setText(intent.getStringExtra("subject"));
        name.setText(intent.getStringExtra("name"));
        company.setText(intent.getStringExtra("company"));
        birthday.setText(intent.getStringExtra("birthday"));
        int avatar = intent.getIntExtra("avatar",R.drawable.ic_launcher);
        avt.setImageResource(avatar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thongtin_giaovien, menu);
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
                Move move = new Move(this,GioithieuActivity.class);
                move.execute();
                break;
            }
            case R.id.thongbao: {
                Move move = new Move(this,ThongbaoActivity.class);
                move.execute();
                break;
            }
            case R.id.showclass: {
                Move move = new Move(this,ShowClass.class);
                move.execute();
                break;
            }
            case R.id.add:
            {
                Move move = new Move(this,ThemLopHoc.class);
                move.execute();
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }
}
