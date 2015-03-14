package com.bmo.backend;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Packet;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Master extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("service started", "started");
		// ChatManager chay = MaintainConnection.connection.getChatManager();

		return super.onStartCommand(intent, flags, startId);
	}
}
