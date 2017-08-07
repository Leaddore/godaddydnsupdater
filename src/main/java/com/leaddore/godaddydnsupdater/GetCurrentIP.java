package com.leaddore.godaddydnsupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * The Class GetCurrentIP.
 */
public class GetCurrentIP {

	private static final String URL = "http://ipinfo.io/json";
	private static final Logger LOGGER = LoggerFactory.getLogger(GetCurrentIP.class);

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getIp() {

		IpInfoJSONReply ipReply = null;
		BufferedReader reader = null;

		try {
			final HttpClient client = HttpClientBuilder.create().build();

			final HttpGet request = new HttpGet(URL);

			final HttpResponse response = client.execute(request);

			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			final StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			final Gson gson = new Gson();

			ipReply = gson.fromJson(result.toString(), IpInfoJSONReply.class);
		} catch (final JsonSyntaxException e) {
			LOGGER.debug("JsonSyntaxException", e);
		} catch (final UnsupportedOperationException e) {
			LOGGER.debug("UnsupportedOperationException", e);
		} catch (final ClientProtocolException e) {
			LOGGER.debug("ClientProtocolException", e);
		} catch (final IOException e) {
			LOGGER.debug("IOException", e);
		} finally {
			try {

				if (reader != null) {
					reader.close();
				}

			} catch (final IOException e) {
				LOGGER.debug("IOException", e);
			}

		}

		return ((ipReply == null) || (ipReply.getIp() == null) ? "No Results found" : ipReply.getIp());

	}

}
