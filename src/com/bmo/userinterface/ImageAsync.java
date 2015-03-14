package com.bmo.userinterface;

import java.io.FileInputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bmo.backend.Chat;
import com.bmo.backend.MaintainConnection;

public class ImageAsync extends AsyncTask<String, Void, String> {
	String the_url;
	FileInputStream fstrm;
	HttpClient httpClient;
	HttpPost httpPost;
	Context ctx;
	MultiUserChat muc;

	public ImageAsync(Context context, FileInputStream fstream,
			HttpClient hClient, HttpPost hPost) {
		fstrm = fstream;
		httpClient = hClient;
		httpPost = hPost;
		ctx = context;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		Upload upload = new Upload();

		try {
			the_url = upload.post(fstrm, httpClient, httpPost);
		} catch (JSONException e) {

			Log.e("error at do in background", e.toString());
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		sendMessageToGroup();

		Log.d("successful  image upload", the_url);

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	public void sendMessageToGroup() {

		if (muc != null && muc.isJoined()) {

			Chat.sendMessage(MaintainConnection.connection,
					"security@conference.ippfar.devs.mobi", the_url);
		} else {
			muc = new MultiUserChat(MaintainConnection.connection,
					"security@conference.ippfar.devs.mobi");

			try {
				muc.join(MaintainConnection.connection.getUser());
			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Chat.sendMessage(MaintainConnection.connection,
					"security@conference.ippfar.devs.mobi", the_url);
		}
	}
}
