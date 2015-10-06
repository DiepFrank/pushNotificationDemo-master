package com.iakremera.pushnotificationdemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

import javax.xml.transform.Result;

public class MainActivity extends Activity implements OnClickListener {
	EditText edt_thongbao;
	TextView textView;
	Button push,move,move2;
    ProgressBar progressBar;
    RelativeLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "c05IjgVFuvO7R4BvGt9qqxYCFSPPgSwjz8Wg7lQh", "pztLyjUrJB0CcljxjJ312YstBrHcSg7kC1ADmcQz");
		PushService.setDefaultPushCallback(this, MainActivity.class);
		edt_thongbao = (EditText) findViewById(R.id.edt_thongbao);
        layout = (RelativeLayout) findViewById(R.id.layout);
        move2 = (Button) findViewById(R.id.move2);
        move2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ThemLopHoc.class);
                startActivity(intent);
            }
        });
		ParseInstallation.getCurrentInstallation().saveInBackground();
		push = (Button)findViewById(R.id.senPushB);
		push.setOnClickListener(this);
        move = (Button) findViewById(R.id.move);
        move.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,ShowClass.class);
                startActivity(intent);
            }
        });
	}
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	class SendNotification extends AsyncTask<String,Integer,String>

	{
        Activity context;
        public SendNotification(Activity cxt)
        {
            context=cxt;
        }
        @Override
		protected String doInBackground(String... strings) {
            String s = edt_thongbao.getText().toString();
            JSONObject obj;
            try {
                obj =new JSONObject();
                obj.put("alert",s);
                obj.put("action","com.iakremera.pushnotificationdemo.UPDATE_STATUS");
                obj.put("customdata","My string");

                ParsePush push = new ParsePush();
                ParseQuery query = ParseInstallation.getQuery();


                // Notification for Android users
                query.whereEqualTo("deviceType", "android");
                push.setQuery(query);
                push.setData(obj);
                push.sendInBackground();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			return s;
		}
	}
	@Override
	public void onClick(View v) {
        if(isOnline()) {
            final SendNotification send = new SendNotification(MainActivity.this);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    send.execute();
                }
            });
        }
        else
        {
            Toast.makeText(MainActivity.this,"Check your Internet connect",Toast.LENGTH_SHORT).show();

        }
	}

	
}
