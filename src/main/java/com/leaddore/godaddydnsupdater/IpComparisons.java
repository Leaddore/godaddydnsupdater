package com.leaddore.godaddydnsupdater;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpComparisons {

	private static final Logger LOGGER = LoggerFactory.getLogger(IpComparisons.class);

	/** The get current ip. */
	private GetCurrentIP getCurrentIp;

	/** The go daddy ip functions. */
	private GoDaddyIpFunctions goDaddyIpFunctions;

	private static final String NO_RESULTS_STRING = "NO RESULTS FOUND";

	public boolean compareIpAddresses() throws IOException {

		boolean isSame = false;

		final String currentIp = getGetCurrentIp().getIp();

		final String goDaddyIp = getGoDaddyIpFunctions().getIpAddress();

		if (!(currentIp.toLowerCase().contains(NO_RESULTS_STRING.toLowerCase())
				|| goDaddyIp.toLowerCase().contains(NO_RESULTS_STRING.toLowerCase()))) {
			if (currentIp.equals(goDaddyIp)) {

				LOGGER.debug("GoDaddy IP listed as {}, Current Internet IP is {}, addresses are the same.", goDaddyIp,
						currentIp);
				isSame = true;

			} else {

				LOGGER.debug("GoDaddy IP listed as {}, Current Internet IP is {}, addresses are different.", goDaddyIp,
						currentIp);
				LOGGER.debug("Setting GoDaddy IP address to {}.", currentIp);

				getGoDaddyIpFunctions().setIpAddress(currentIp);

			}
		} else {
			LOGGER.debug("Issue with IP Comparison, current ip is {} goDaddy ip is {}", currentIp, goDaddyIp);
		}

		return isSame;

	}

	/**
	 * Gets the gets the current ip.
	 *
	 * @return the gets the current ip
	 */
	public GetCurrentIP getGetCurrentIp() {
		if (getCurrentIp == null) {
			getCurrentIp = new GetCurrentIP();
		}
		return getCurrentIp;
	}

	/**
	 * Sets the gets the current ip.
	 *
	 * @param getCurrentIp
	 *            the new gets the current ip
	 */
	public void setGetCurrentIp(GetCurrentIP currentIP) {
		getCurrentIp = currentIP;
	}

	/**
	 * Gets the go daddy ip functions.
	 *
	 * @return the go daddy ip functions
	 */
	public GoDaddyIpFunctions getGoDaddyIpFunctions() {
		if (goDaddyIpFunctions == null) {
			goDaddyIpFunctions = new GoDaddyIpFunctions();
		}
		return goDaddyIpFunctions;
	}

	/**
	 * Sets the go daddy ip functions.
	 *
	 * @param goDaddyIpFunctions
	 *            the new go daddy ip functions
	 */
	public void setGoDaddyIpFunctions(GoDaddyIpFunctions ipFunctions) {
		goDaddyIpFunctions = ipFunctions;
	}

}
