package com.leaddore.godaddydnsupdater;

import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GetCurrentIP.class)
public class GetCurrentIPTest {

	@Mock
	private HttpClient mockClient;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testHappyPath() {

		PowerMockito.mockStatic(GetCurrentIP.class);

	}

}
