package com.iakremera.pushnotificationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class ShowClass extends Activity {
    Spinner subject,grade;
    ImageButton search_bt;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_class);
        Parse.initialize(this, "c05IjgVFuvO7R4BvGt9qqxYCFSPPgSwjz8Wg7lQh", "pztLyjUrJB0CcljxjJ312YstBrHcSg7kC1ADmcQz");

        subject = (Spinner) findViewById(R.id.search_subject);
        grade = (Spinner) findViewById(R.id.search_grade);
        search_bt = (ImageButton) findViewById(R.id.bt_searchclass);
        lv = (ListView) findViewById(R.id.listView);

    }
    public void search_class(View view)
    {
    String sub = subject.getSelectedItem().toString();
    String gra = grade.getSelectedItem().toString();
    ParseQuery query = new ParseQuery("KhoaHoc");
    query.whereEqualTo("subject",sub);
    query.whereEqualTo("grade",gra);
    query.findInBackground(new FindCallback() {
        @Override
        public void done(List<ParseObject> list, ParseException e) {
            String a = list.get(0).getString("grade");

            ArrayList<KhoaHoc> list_khoahoc = null;
            for(int i=0;i<list.size();i++)
            {
                if (list_khoahoc != null) {

                    list_khoahoc.add(new KhoaHoc(list.get(i).getString("subject"),
                                                      list.get(i).getString("teacher"),
                                                      list.get(i).getString("grade"),
                                                      list.get(i).getString("start"),
                                                      list.get(i).getString("end"),
                                                      list.get(i).getString("day")
                    )   );
                }
            }
            int x = list.size();
                    Toast.makeText(ShowClass.this,""+x,Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
