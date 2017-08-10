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

	private HttpClient client;

	private HttpGet request;

	private HttpResponse response;

	private Gson gson;

	private BufferedReader reader;

	private StringBuilder result;

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

		try {

			client = getClient();

			request = getRequest();

			response = client.execute(request);

			reader = getReader();

			result = getResult();

			String line = "";

			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			gson = getGson();

			ipReply = gson.fromJson(result.toString(), IpInfoJSONReply.class);

		} catch (final JsonSyntaxException e) {
			LOGGER.debug("JsonSyntaxException", e);
		} catch (final UnsupportedOperationException e) {
			LOGGER.debug("UnsupportedOperationException", e);
		} catch (final ClientProtocolException e) {
			LOGGER.debug("ClientProtocolException", e);
		} catch (final IOException e) {
			LOGGER.debug("Input/Output Exception thrown", e);
		} finally {
			try {

				if (reader != null) {
					reader.close();
				}

			} catch (final IOException e) {
				LOGGER.debug("Input/Output Exception thrown in the finally block", e);
			}

		}

		return ((ipReply == null) || (ipReply.getIp() == null) ? "No Results found" : ipReply.getIp());

	}

	public HttpClient getClient() {
		if (client == null) {
			client = HttpClientBuilder.create().build();
		}
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public HttpGet getRequest() {
		if (request == null) {
			request = new HttpGet(URL);
		}
		return request;
	}

	public void setRequest(HttpGet request) {
		this.request = request;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public BufferedReader getReader() {
		if (reader == null) {
			try {
				reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			} catch (final UnsupportedOperationException e) {
				LOGGER.debug("UnsupportedOperationException", e);
			} catch (final IOException e) {
				LOGGER.debug("Input/Output exception thown while trying to allocate the buffered reader.", e);
			}
		}
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public StringBuilder getResult() {
		if (result == null) {
			result = new StringBuilder();

		}
		return result;
	}

	public void setResult(StringBuilder result) {
		this.result = result;
	}

}
