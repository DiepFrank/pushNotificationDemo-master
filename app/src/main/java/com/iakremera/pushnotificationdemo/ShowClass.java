package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class ShowClass extends Activity {
    Spinner subject,grade;
    ProgressDialog progressDialog;
    ImageButton search_bt;
    TextView txttotal,txtstt1,txtcahoc1,txtngayhoc1,txtngayhoc2,txtstart1,txtstart2,txtend1,txtend2,txtcahoc2,txtstt2;
    Button del1,del2;
    String id1,id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);

        setContentView(R.layout.activity_show_class);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        txttotal = (TextView) findViewById(R.id.total);
        txtstt1 = (TextView) findViewById(R.id.stt);
        txtcahoc1 = (TextView) findViewById(R.id.cahoc);
        id1 = id2 =null;
        txtngayhoc1 = (TextView) findViewById(R.id.ngayhoc1);
        txtend1 = (TextView) findViewById(R.id.end);
        txtend2 = (TextView) findViewById(R.id.end2);
        txtstart1 = (TextView) findViewById(R.id.start);
        txtstart2 = (TextView) findViewById(R.id.start2);
        txtcahoc2 = (TextView) findViewById(R.id.cahoc2);
        txtngayhoc2 = (TextView) findViewById(R.id.ngayhoc2);
        txtstt2 = (TextView) findViewById(R.id.stt2);
        del1 = (Button) findViewById(R.id.delete1);
        del2 = (Button) findViewById(R.id.delete2);
        Parse.initialize(this, "c05IjgVFuvO7R4BvGt9qqxYCFSPPgSwjz8Wg7lQh", "pztLyjUrJB0CcljxjJ312YstBrHcSg7kC1ADmcQz");
        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject object = new ParseObject("KhoaHoc");
                object.remove(id1);
            }
        });
        subject = (Spinner) findViewById(R.id.search_subject);
        grade = (Spinner) findViewById(R.id.search_grade);
        search_bt = (ImageButton) findViewById(R.id.bt_searchclass);

    }
    public void search_class(View view)
    {   progressDialog.setCancelable(true);
        progressDialog.setTitle("Finding class...");
        progressDialog.setMessage("Wait a minute guy :D ");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        search_bt.setEnabled(false);
    String sub = subject.getSelectedItem().toString();
    String gra = grade.getSelectedItem().toString();
    ParseQuery query = new ParseQuery("KhoaHoc");
    query.whereEqualTo("subject",sub);
    query.whereEqualTo("grade", gra);
    query.findInBackground(new FindCallback() {
        @Override
        public void done(List<ParseObject> list, ParseException e) {
            progressDialog.dismiss();
            search_bt.setEnabled(true);
            if(list.size()<=0)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowClass.this);
                builder.setCancelable(true);
                builder.setMessage("Not found");
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            if(list.size()==1) {
                String cahoc1 = list.get(0).getString("ca");
                String total = list.get(0).getString("total");
                String stt1 = list.get(0).getString("stt");
                String ngayhoc1 = list.get(0).getString("day");
                String start1 = list.get(0).getString("start");
                String end1 = list.get(0).getString("end");
                id1 = list.get(0).getString("objectId");
                ParseObject object = new ParseObject("KhoaHoc");
                txtcahoc1.setText(cahoc1);
                txtend1.setText(end1);
                txtstart1.setText(start1 + " - ");
                txttotal.setText(total);
                txtstt1.setText(stt1);
                txtngayhoc1.setText(ngayhoc1);
            }
                if (list.size()==2) {
                    String cahoc1 = list.get(0).getString("ca");
                    String total = list.get(0).getString("total");
                    String stt1 = list.get(0).getString("stt");
                    String ngayhoc1 = list.get(0).getString("day");
                    String start1 = list.get(0).getString("start");
                    String end1 = list.get(0).getString("end");
                    id1 = list.get(0).getString("objectId");
                    ParseObject object = new ParseObject("KhoaHoc");
                    txtcahoc1.setText(cahoc1);
                    txtend1.setText(end1);
                    txtstart1.setText(start1 + " - ");
                    txttotal.setText(total);
                    txtstt1.setText(stt1);
                    txtngayhoc1.setText(ngayhoc1);
                    String cahoc2 = list.get(1).getString("ca");
                    String stt2 = list.get(1).getString("stt");
                    String ngayhoc2 = list.get(1).getString("day");
                    String start2 = list.get(1).getString("start");
                    String end2 = list.get(1).getString("end");
                    txtcahoc2.setText(cahoc2);
                    txtend2.setText(end2);
                    txtstart2.setText(start2 + " - ");
                    txtstt2.setText(stt2);
                    txtngayhoc2.setText(ngayhoc2);
                }
            }

    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_class, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.info: {
                Move move = new Move(ShowClass.this,GioithieuActivity.class);
                move.execute();
                break;
            }
            case R.id.thongbao: {
                Move move = new Move(ShowClass.this,ThongbaoActivity.class);
                move.execute();
                break;
            }
            case R.id.showclass: {
                Move move = new Move(ShowClass.this,ShowClass.class);
                move.execute();
                break;
            }
            case R.id.add:
            {
                Move move = new Move(ShowClass.this,ThemLopHoc.class);
                move.execute();
                break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
