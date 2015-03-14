package com.bmo.userinterface;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.bmo.backend.Chat;
import com.bmo.backend.InitializeConnection;
import com.bmo.backend.MaintainConnection;
import com.bmo.backend.Master;
import com.bmo.telephony.SmsSend;
import com.sample.pdfwebviewer.R;

public class SenderActivity extends Activity {

	String file_url = "https://api.parse.com/1/files/ic_launch.png";

	FileInputStream fstrm;
	Map<String, String> postMap = new HashMap<String, String>();
	List<NameValuePair> listKeyValue = new ArrayList<NameValuePair>(5);
	File file = null;
	Handler myHandler;
	Button sendFile;
	final int DO_UPDATE_TEXT = 0;
	final int DO_THAT = 1;
	SmsSend send;
	String phoneNumer = "+254727383066";

	String messageToSend = "we did not invent the algorithm. the algorithm constantly "
			+ "finds jesus. the algorithm killed jeeves. the algorithm "
			+ "is banned in china. the algorithm is from jersey. the algorithm "
			+ "constantly finds jesus. this is not the algorithm. this is close ";

	String jid;
	String event = "sendsms";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.webview);
		serverConnection();

//		send.sendSms(SenderActivity.this, "+254727383066", "blah");

		sendFile = (Button) findViewById(R.id.button1);

		sendFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// sender 2.2 finn
				Chat.sendMessage(event, MaintainConnection.connection,
						"jake@bmo.com", phoneNumer, messageToSend);
			}

		});

	}

	public void serverConnection() {

		// Looper.prepare();
		myHandler = new Handler() {

			public void handleMessage(Message msg) {
				final int what = msg.what;
				switch (what) {
				case DO_UPDATE_TEXT:

					Intent i = new Intent(SenderActivity.this, Master.class);
					startService(i);
					Log.i("message received", "App connected to server");
					Toast.makeText(SenderActivity.this, "connected to server",
							3000).show();

					MessageTypeFilter typeFilter = new MessageTypeFilter(
							Type.normal);

					PacketListener pl = new PacketListener() {

						@Override
						public void processPacket(Packet packet) {
							// TODO Auto-generated method stub
							org.jivesoftware.smack.packet.Message message = (org.jivesoftware.smack.packet.Message) packet;
							String from = message.getFrom();

							String event = message.getBody("event");
							String phoneNumber = message.getBody("phoneNumber");
							String messageToSend = message.getBody();
							
							
							
							Log.d("received message", messageToSend + from
									+ event + phoneNumber);

							myHandler.sendEmptyMessage(1);
						}
					};

					MaintainConnection.connection.addPacketListener(pl, null);
					break;
				case DO_THAT:
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage("+254727383066", null, "messageToSend", null, null);
					break;
				}
			}
		};

		InitializeConnection.initialize(this, myHandler);
	}

}
