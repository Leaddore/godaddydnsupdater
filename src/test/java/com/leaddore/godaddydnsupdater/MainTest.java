package com.leaddore.godaddydnsupdater;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainTest {

	@Mock
	private GoDaddyIpFunctions gdipfunctionsMock;

	@Mock
	private GetCurrentIP getCurrentIpMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testMainHappyPath() throws IOException, InterruptedException {
	}

}
