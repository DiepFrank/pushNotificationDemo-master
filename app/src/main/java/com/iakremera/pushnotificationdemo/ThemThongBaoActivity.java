package com.iakremera.pushnotificationdemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

import java.util.Calendar;
import java.util.Date;

public class ThemThongBaoActivity extends Activity implements OnClickListener {
	EditText edt_thongbao;
	Button push,move,move2;
    ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.themthongbao);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        Parse.initialize(this, "c05IjgVFuvO7R4BvGt9qqxYCFSPPgSwjz8Wg7lQh", "pztLyjUrJB0CcljxjJ312YstBrHcSg7kC1ADmcQz");
		PushService.setDefaultPushCallback(this, ThongbaoActivity.class);
		edt_thongbao = (EditText) findViewById(R.id.edt_thongbao);
        edt_thongbao.setText(null);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		push = (Button)findViewById(R.id.senPushB);
		push.setOnClickListener(this);

	}
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void showTime(Context context)
    {
        Calendar c = Calendar.getInstance();
        String time = "Send ";
        time = time + c.get(Calendar.HOUR_OF_DAY)
                + ":"
                + c.get(Calendar.MINUTE)
                +" at "
                + c.get(Calendar.DAY_OF_MONTH)
                + "/"
                + c.get(Calendar.MONTH)
        ;
        Toast.makeText(context,time,Toast.LENGTH_LONG).show();

    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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
	class SendNotification extends AsyncTask<String,Integer,String>

	{
        Activity context;
        public SendNotification(Activity cxt)
        {
            context=cxt;
        }
        @Override
        protected void onPreExecute() {
        progressDialog = new ProgressDialog(ThemThongBaoActivity.this);
        progressDialog.setMessage("Processing....");
        progressDialog.setTitle("Please waiting while The notification is sending to Server. It depends on your Internet connection")     ;
            progressDialog.setCancelable(true);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
        @Override
		protected String doInBackground(String... strings) {
            String s = edt_thongbao.getText().toString();
            Date date = new Date();
            JSONObject obj;
            if (s == null) {
            Toast.makeText(ThemThongBaoActivity.this,"Can't send because this is an empty notification",Toast.LENGTH_LONG).show();
            return null;

            }
            else {
                try {
                    obj = new JSONObject();
                    obj.put("alert", s);
                    obj.put("action", "com.iakremera.pushnotificationdemo.UPDATE_STATUS");
                    obj.put("customdata", "My string");

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
        protected void onPostExecute(String s) {
            showTime(ThemThongBaoActivity.this);
            edt_thongbao.setText(null);
            if(progressDialog!=null)
            {
                progressDialog.dismiss();
            }
        }
    }
	@Override
	public void onClick(View v) {
        if(isOnline()) {
            final SendNotification send = new SendNotification(ThemThongBaoActivity.this);
            send.execute();
        }
        else
        {
            Toast.makeText(ThemThongBaoActivity.this,"Check your Internet connect",Toast.LENGTH_SHORT).show();

        }
	}

	
}
