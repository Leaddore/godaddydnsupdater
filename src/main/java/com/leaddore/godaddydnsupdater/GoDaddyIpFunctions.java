package com.leaddore.godaddydnsupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The Class GoDaddyIpFunctions.
 */
public class GoDaddyIpFunctions {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GoDaddyIpFunctions.class);

	/** The Constant DOMAIN_NAME. */
	static final String DOMAIN_NAME = ""; // The domain name that you wish to update

	/** The Constant KEY. */
	static final String KEY = ""; // The developer API Key that you received from
	// GoDaddy

	/** The Constant SECRET. */
	static final String SECRET = ""; // The secret that matches the developer API key from GoDaddy

	/** The Constant NAME. */
	static final String NAME = "@"; // The name of the DNS record you wish to monitor and update, usually defaults
									// to @

	/** The Constant TYPE. */
	static final String TYPE = "A"; // The type of DNS record you want to track and update. Almost always an A
	// record.

	/** The Constant TTL. */
	static final String TTL = "3600"; // The TTL for the record

	/** The Constant REQUEST_URL. */
	static final String REQUEST_URL = "https://api.godaddy.com/v1/domains/" + DOMAIN_NAME + "/records/" + TYPE + "/"
			+ NAME; // Request URL from GoDaddy API, unless GoDaddy updates their API, this should
					// not need to change.

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getIpAddress() throws IOException {

		final HttpClient client = HttpClientBuilder.create().build();

		final HttpGet request = new HttpGet(REQUEST_URL);

		request.setHeader("Authorization", "sso-key " + KEY + ":" + SECRET);

		final HttpResponse response = client.execute(request);

		final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		final StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}

		final String cleanResult = result.substring(1, result.length() - 1);

		final Gson gson = new Gson();

		final GoDaddyJSONReply gdJson = gson.fromJson(cleanResult, GoDaddyJSONReply.class);

		return gdJson.getData();

	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress
	 *            the new ip address
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setIpAddress(String ipAddress) throws IOException {

		final HttpClient client = HttpClientBuilder.create().build();

		final HttpPut request = new HttpPut(REQUEST_URL);

		request.setHeader("Authorization", "sso-key " + KEY + ":" + SECRET);
		request.setHeader("Content-Type", "application/json");

		final GoDaddyJSONReply jsonReply = new GoDaddyJSONReply();

		jsonReply.setData(ipAddress);
		jsonReply.setTtl("3600");

		final StringEntity entity = new StringEntity(jsonReply.toString());

		request.setEntity(entity);

		final HttpResponse response = client.execute(request);

		final int statusCode = response.getStatusLine().getStatusCode();

		if (200 == statusCode) {
			LOGGER.debug("Ipaddress successfully updated");
		} else {
			LOGGER.debug("Error updating ip address, error code was: {}", statusCode);
		}

	}

}
