package com.leaddore.godaddydnsupdater;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GoDaddyJSONReplyTest {

	private GoDaddyJSONReply reply;

	@Before
	public void setUp() throws Exception {

		reply = new GoDaddyJSONReply();

		reply.setData("10.0.0.1");
		reply.setName("TestName");
		reply.setType("A");
		reply.setTtl("3600");
	}

	@Test
	public void testToString() {

		assertTrue("These should have been equal.",
				"{ \"type\":\"A\", \"name\":\"TestName\", \"data\":\"10.0.0.1\", \"ttl\":3600 }"
						.equals(reply.toString()));

	}

}
