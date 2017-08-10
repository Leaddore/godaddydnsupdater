package com.leaddore.godaddydnsupdater;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GetCurrentIPTest {

	@Mock
	private HttpClient mockClient;

	@Mock
	private HttpGet mockRequest;

	@Mock
	private HttpResponse mockResponse;

	@Mock
	private InputStream mockInputStreamReader;

	@Mock
	private HttpEntity mockEntity;

	@Mock
	private BufferedReader mockReader;

	@Mock
	private IpInfoJSONReply mockIpReply;

	private GetCurrentIP ip;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		ip = new GetCurrentIP();

		ip.setClient(mockClient);
		ip.setRequest(mockRequest);
		ip.setResponse(mockResponse);
		ip.setReader(mockReader);

	}

	@Test
	public void testHappyPath() throws ClientProtocolException, IOException {

		when(mockClient.execute(any(HttpGet.class))).thenReturn(mockResponse);
		when(mockResponse.getEntity()).thenReturn(mockEntity);
		when(mockEntity.getContent()).thenReturn(mockInputStreamReader);
		when(mockReader.readLine()).thenReturn("{ip=0.0.0.0}").thenReturn(null);

		assertTrue("Ip address should be 0.0.0.0", "0.0.0.0".equals(ip.getIp()));

	}

}
