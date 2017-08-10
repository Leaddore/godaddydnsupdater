package com.leaddore.godaddydnsupdater;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IpComparisonsTest {

	@Mock
	private GoDaddyIpFunctions mockGoDaddyIpFunctions;

	@Mock
	private GetCurrentIP mockGetCurrentIp;

	private IpComparisons ipCompare;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		ipCompare = new IpComparisons();

		ipCompare.setGetCurrentIp(mockGetCurrentIp);
		ipCompare.setGoDaddyIpFunctions(mockGoDaddyIpFunctions);
	}

	@Test
	public void testHappyPath() throws IOException {

		when(mockGetCurrentIp.getIp()).thenReturn("0.0.0.0");
		when(mockGoDaddyIpFunctions.getIpAddress()).thenReturn("0.0.0.0");

		assertTrue("Ip Addresses should be the same", ipCompare.compareIpAddresses());

	}

	@Test
	public void testIpsNotTheSame() throws IOException {

		when(mockGetCurrentIp.getIp()).thenReturn("0.0.0.1");
		when(mockGoDaddyIpFunctions.getIpAddress()).thenReturn("0.0.0.0");

		assertFalse("Ip Addresses should be the same", ipCompare.compareIpAddresses());

	}

}
