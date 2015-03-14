package com.bmo.telephony;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SmsSend {

	public void sendSms(Context context, String phoneNumber,
			String message) {
		Log.i("Send SMS", "");

		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, null, message, null, null);
			Toast.makeText(context, "SMS sent.", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(context, "SMS faild, please try again.",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}
