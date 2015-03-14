package com.bmo.userinterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bmo.backend.InitializeConnection;
import com.bmo.backend.Master;
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

	ImageView im;
	HttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(file_url);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		serverConnection();
		imageRetrieval();
		sendFile = (Button) findViewById(R.id.button1);
		sendFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});

	}

	public void serverConnection() {

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
					break;
				case DO_THAT:
					// doThat();
					break;
				}
			}
		};

		InitializeConnection.initialize(this, myHandler);
	}

	public void imageRetrieval() {

		file = new File(Environment.getExternalStorageDirectory().toString()
				+ "/" + "ic_launch.png");

		try {
			fstrm = new FileInputStream(Environment
					.getExternalStorageDirectory().toString()
					+ "/"
					+ "ic_launch.png");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Bitmap thumb_nail = ThumbnailUtils.extractThumbnail(
				BitmapFactory.decodeStream(fstrm), 75, 75);
	}

}
