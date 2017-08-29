package com.leaddore.godaddydnsupdater;

import java.io.IOException;

/**
 * The Class Main.
 */
public class Main {

	private static IpComparisons ipCompare;

	private static int sleepTimer = 3600000;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String... args) throws IOException, InterruptedException {

		for (;;) {
			getIpCompare().compareIpAddresses();
			Thread.sleep(sleepTimer);

		}

	}

	public static IpComparisons getIpCompare() {
		if (ipCompare == null) {
			ipCompare = new IpComparisons();
		}
		return ipCompare;
	}

	public static void setIpCompare(IpComparisons ipCompare) {
		Main.ipCompare = ipCompare;
	}

}
