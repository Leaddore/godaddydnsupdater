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

    /** The Constant URL. */
    private static final String URL = "http://ipinfo.io/json";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCurrentIP.class);

    /** The client. */
    private HttpClient client;

    /** The request. */
    private HttpGet request;

    /** The response. */
    private HttpResponse response;

    /** The gson. */
    private Gson gson;

    /** The reader. */
    private BufferedReader reader;

    /** The result. */
    private StringBuilder result;

    /**
     * Gets the ip.
     *
     * @return the ip
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

    /**
     * Gets the client.
     *
     * @return the client
     */
    public HttpClient getClient() {
        if (client == null) {
            client = HttpClientBuilder.create().build();
        }
        return client;
    }

    /**
     * Sets the client.
     *
     * @param client the new client
     */
    public void setClient(HttpClient client) {
        this.client = client;
    }

    /**
     * Gets the request.
     *
     * @return the request
     */
    public HttpGet getRequest() {
        if (request == null) {
            request = new HttpGet(URL);
        }
        return request;
    }

    /**
     * Sets the request.
     *
     * @param request the new request
     */
    public void setRequest(HttpGet request) {
        this.request = request;
    }

    /**
     * Gets the response.
     *
     * @return the response
     */
    public HttpResponse getResponse() {
        return response;
    }

    /**
     * Sets the response.
     *
     * @param response the new response
     */
    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    /**
     * Gets the gson.
     *
     * @return the gson
     */
    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * Sets the gson.
     *
     * @param gson the new gson
     */
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    /**
     * Gets the reader.
     *
     * @return the reader
     */
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

    /**
     * Sets the reader.
     *
     * @param reader the new reader
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    public StringBuilder getResult() {
        if (result == null) {
            result = new StringBuilder();

        }
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(StringBuilder result) {
        this.result = result;
    }

}
