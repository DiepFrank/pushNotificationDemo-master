package com.iakremera.pushnotificationdemo;

import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (intent == null)
			{
			}
			else
			{
                ThongbaoSQLiteOpenHelper helper = new ThongbaoSQLiteOpenHelper(context);
				String action = intent.getAction();
				if (action.equals("com.iakremera.pushnotificationdemo.UPDATE_STATUS"))
				{
                        Calendar c = Calendar.getInstance();
                        String time = "";
                        time = time + c.get(Calendar.HOUR_OF_DAY)
                                + ":"
                                + c.get(Calendar.MINUTE)
                                +" at "
                                + c.get(Calendar.DAY_OF_MONTH)
                                + "/"
                                + c.get(Calendar.MONTH) ;


					String channel = intent.getExtras().getString("com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
					Iterator itr = json.keys();
					String s = json.getString("alert");
					while (itr.hasNext()) {
						String key = (String) itr.next();
						if (key.equals("customdata"))
						{

                            Thongbao thongbao = new Thongbao(s,time);
                            SQLiteDatabase db = helper.getReadableDatabase();
                            if(db!=null) {
                                helper.insert_tb(db, thongbao);
                                Toast.makeText(context,"Received notification",Toast.LENGTH_LONG).show();
                            }
                            db.close();
						}
					}
				}
			}

		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
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
}
