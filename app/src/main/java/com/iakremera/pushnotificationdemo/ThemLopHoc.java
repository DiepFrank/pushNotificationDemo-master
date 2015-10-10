package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Diep_Chelsea on 03/10/2015.
 */
public class ThemLopHoc extends Activity {
    Spinner subject,grade,teacher,end,start,day,total,stt,ca;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themlophoc);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);

        Parse.initialize(this, "c05IjgVFuvO7R4BvGt9qqxYCFSPPgSwjz8Wg7lQh", "pztLyjUrJB0CcljxjJ312YstBrHcSg7kC1ADmcQz");
        total = (Spinner) findViewById(R.id.total);
        stt = (Spinner) findViewById(R.id.stt);
        ca = (Spinner) findViewById(R.id.ca);
        subject = (Spinner) findViewById(R.id.subject);
        teacher = (Spinner) findViewById(R.id.teacher);
        grade = (Spinner) findViewById(R.id.grade);
        end = (Spinner) findViewById(R.id.end);
        start = (Spinner) findViewById(R.id.start);
        day = (Spinner) findViewById(R.id.day);
        add = (Button) findViewById(R.id.bt_add);

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void add_monhoc(View view) {
        if (isOnline()) {
            String monhoc, giaovien, lop, bd, kt, thu, cahoc, tong, sttca;
            monhoc = subject.getSelectedItem().toString();
            giaovien = teacher.getSelectedItem().toString();
            lop = grade.getSelectedItem().toString();
            bd = start.getSelectedItem().toString();
            kt = end.getSelectedItem().toString();
            thu = day.getSelectedItem().toString();
            sttca = stt.getSelectedItem().toString();
            tong = total.getSelectedItem().toString();
            cahoc = ca.getSelectedItem().toString();
            ParseObject data = new ParseObject("KhoaHoc");
            data.put("subject", monhoc);
            data.put("teacher", giaovien);
            data.put("grade", lop);
            data.put("end", kt);
            data.put("start", bd);
            data.put("day", thu);
            data.put("ca", cahoc);
            data.put("total", tong);
            data.put("stt", sttca);
            data.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Toast.makeText(ThemLopHoc.this, "Done", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(ThemLopHoc.this,"Check your Internet connect",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_themlophoc, menu);
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
                Intent intent = new Intent(ThemLopHoc.this, GioithieuActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.thongbao: {
                Intent intent = new Intent(ThemLopHoc.this,ThongbaoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.showclass: {
                Intent intent = new Intent(ThemLopHoc.this, ShowClass.class);
                startActivity(intent);
                break;
            }
            case R.id.add:
            {
                Intent intent = new Intent(this, ThemLopHoc.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }
}
