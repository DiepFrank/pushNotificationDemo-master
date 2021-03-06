package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class ThongbaoActivity extends Activity {
    ListView listView;
    Button add_tb,del;
    Tb_Adapter adapter;
    ArrayList<Thongbao> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        arrayList = new ArrayList<Thongbao>();
        add_tb = (Button) findViewById(R.id.add_tb);
        del = (Button) findViewById(R.id.delete);
        add_tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ThongbaoActivity.this,ThemThongBaoActivity.class);
                startActivity(intent);
            }
        });
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        listView = (ListView) findViewById(R.id.listView2);
        ThongbaoSQLiteOpenHelper openHelper = new ThongbaoSQLiteOpenHelper(ThongbaoActivity.this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        if(db!=null) {
                Cursor cursor = db.query("thongbao", new String[]{"id", "content","time"}, null, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String s = cursor.getString(1);
                    String d = cursor.getString(2);
                    arrayList.add(new Thongbao(s, d));
                    cursor.moveToNext();
                }
            Collections.reverse(arrayList);
            Tb_Adapter adapter = new Tb_Adapter(this,R.layout.custom_tb,arrayList);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThongbaoActivity.this);
                    builder.setTitle("Noi dung thong bao");
                    builder.setCancelable(true);
                    builder.setMessage(arrayList.get(i).content);
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        else
        {
            arrayList.add(new Thongbao("Welcome","few second ago"));
            adapter = new Tb_Adapter(this,R.layout.custom_tb,arrayList);
            listView.setAdapter(adapter);
        }
    }
    public void delete(View view)
    {
        ThongbaoSQLiteOpenHelper helper = new ThongbaoSQLiteOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null)
        {
            db.delete("thongbao", null, null);
            arrayList.clear();
            Intent intent = new Intent(ThongbaoActivity.this,ThongbaoActivity.class);
            startActivity(intent);

        }
    }
    public void showTime(Context context) {
        Calendar c = Calendar.getInstance();
        String time = "Send ";
        time = time + c.get(Calendar.DAY_OF_MONTH)
                + ":"
                + c.get(Calendar.MONTH)
                +" at "
                + c.get(Calendar.HOUR_OF_DAY)
                + "/"
                + c.get(Calendar.MINUTE)
        ;
        Toast.makeText(context,time,Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thongbao, menu);
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