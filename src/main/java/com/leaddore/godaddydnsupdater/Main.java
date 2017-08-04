package com.leaddore.godaddydnsupdater;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Main.
 */
public class Main {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	/** The Constant SLEEP_TIME_IN_MILIS. */
	private static final int SLEEP_TIME_IN_MILIS = 3600000;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {

		while (true) {

			final String currentIp = GetCurrentIP.getIp();

			final String goDaddyIp = GoDaddyIpFunctions.getIpAddress();

			if (currentIp.equals(goDaddyIp)) {

				LOGGER.debug("GoDaddy IP listed as {}, Current Internet IP is {}, addresses are the same.", goDaddyIp,
						currentIp);
			} else {
				LOGGER.debug("GoDaddy IP listed as {}, Current Internet IP is {}, addresses are different.", goDaddyIp,
						currentIp);
				LOGGER.debug("Setting GoDaddy IP address to {}.", currentIp);

				GoDaddyIpFunctions.setIpAddress(currentIp);

			}

			Thread.sleep(SLEEP_TIME_IN_MILIS);

		}

	}

}
