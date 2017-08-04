package com.leaddore.godaddydnsupdater;

import java.util.StringJoiner;

/**
 * The Class GoDaddyJSONReply.
 */
public class GoDaddyJSONReply {

	/** The type. */
	private String type;

	/** The name. */
	private String name;

	/** The data. */
	private String data;

	/** The ttl. */
	private String ttl;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the ttl.
	 *
	 * @return the ttl
	 */
	public String getTtl() {
		return ttl;
	}

	/**
	 * Sets the ttl.
	 *
	 * @param ttl
	 *            the new ttl
	 */
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		final StringJoiner joiner = new StringJoiner(",");

		final StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (!(type == null)) {

			joiner.add(" \"type\":\"" + type + "\"");

		}

		if (!(name == null)) {

			joiner.add(" \"name\":\"" + name + "\"");

		}
		if (!(data == null)) {
			joiner.add(" \"data\":\"" + data + "\"");

		}

		if (!(ttl == null)) {

			joiner.add(" \"ttl\":" + ttl);

		}

		sb.append(joiner.toString());

		sb.append(" }");

		return sb.toString();

	}

}
