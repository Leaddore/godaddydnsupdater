package com.leaddore.godaddydnsupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

/**
 * The Class GetCurrentIP.
 */
public final class GetCurrentIP {

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getIp() throws ClientProtocolException, IOException {

		final String url = "http://ipinfo.io/json";

		final HttpClient client = HttpClientBuilder.create().build();

		final HttpGet request = new HttpGet(url);

		final HttpResponse response = client.execute(request);

		final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		final StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}

		final Gson gson = new Gson();

		final IpInfoJSONReply ipReply = gson.fromJson(result.toString(), IpInfoJSONReply.class);

		return ipReply.getIp();

	}

}
