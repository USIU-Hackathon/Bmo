package com.bmo.userinterface;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Upload {

	JSONObject imgUr;
	String format, the_url;

	public String post(FileInputStream fstrm, HttpClient httpClient,
			HttpPost httpPost) throws JSONException {

		try {

			JSONObject object = new JSONObject();
			byte[] bytes = IOUtils.toByteArray(fstrm);

			object.put("image", bytes);

			httpPost.addHeader("X-Parse-Application-Id",
					"TWnnekMt5ngtmq0wulVlXAtWjFnxPZET2I3OR3yV");

			httpPost.addHeader("X-Parse-REST-API-Key",
					"ZpvwA1oe3Z0qwMn3UTGg2nW6gLh2nGu01y50jDob");

			httpPost.addHeader("Content-Type", "image/png");

			Log.d("the json to string", object.toString());

			httpPost.setEntity(new ByteArrayEntity(bytes));

			HttpResponse response = httpClient
					.execute((HttpUriRequest) httpPost);

			HttpEntity entity = response.getEntity();

			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append((line + "\n"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// write response to log
			imgUr = new JSONObject(sb.toString());
			the_url = (String) imgUr.get("url");
			Log.d("the url retrieved", the_url);

			Log.d("Http Post Response:", sb.toString());
		} catch (ClientProtocolException e) {
			// Log exception
			e.printStackTrace();
		} catch (IOException e) {
			// Log exception
			e.printStackTrace();
		}
		return the_url;
	}
}