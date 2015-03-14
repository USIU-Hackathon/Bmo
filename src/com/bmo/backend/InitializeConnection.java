package com.bmo.backend;

import android.content.Context;
import android.os.Handler;

public class InitializeConnection {

	public static void initialize(Context context, Handler hand) {
		if (MaintainConnection.connection == null) {
			MaintainConnection.connect(context, hand);

		} else {
			hand.sendEmptyMessage(0);
		}

	}
}
