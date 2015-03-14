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
		MessageTypeFilter typeFilter = new MessageTypeFilter(Type.chat);
		PacketListener pl = new PacketListener() {

			@Override
			public void processPacket(Packet packet) {
				// TODO Auto-generated method stub
				Message message = (Message) packet;
				String from = message.getFrom();
				String mes = message.getBody();
				Log.d("received message", mes);
			}
		};

		MaintainConnection.connection.addPacketListener(pl, typeFilter);
		return super.onStartCommand(intent, flags, startId);
	}
}
