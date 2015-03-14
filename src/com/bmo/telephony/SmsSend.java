package com.bmo.telephony;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

public class SmsSend {

	public void sendSms(Context context, String phoneNumber, String message) {

		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, null, message, null, null);

		} catch (Exception e) {
			Log.e("error sending text", e.toString());

		}
	}
}
