package com.bmo.telephony;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SmsRead {

	public void readSms(Context context) {
		Uri uriSMSURI = Uri.parse("content://sms/inbox");
		Cursor cur = context.getContentResolver().query(uriSMSURI, null, null, null,
				null);
		String sms = "";
		while (cur.moveToNext()) {
			sms += "From :"
					+ cur.getString(cur.getColumnIndexOrThrow("address"))
							.toString()
					+ " : "
					+ cur.getString(cur.getColumnIndexOrThrow("body"))
							.toString() + "\n";
			Log.i("messages", sms);
		}
	}
}
