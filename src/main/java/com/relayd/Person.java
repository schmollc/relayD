package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid = null;
	private Surename surename = null;
	private Forename forename = null;
	private Birthday birthday = null;

	private Person() {
		uuid = UUID.randomUUID();
	}

	public static Person newInstance() {
		return new Person();
	}

	public UUID getUUID() {
		return uuid;
	}

	public Surename getSurename() {
		return surename;
	}

	public void setSurename(Surename aSurename) {
		surename = aSurename;
	}

	public Birthday getBirthday() {
		return birthday;
	}

	public void setBirthday(Birthday aBirthday) {
		birthday = aBirthday;
	}

	public Forename getForename() {
		return forename;
	}

	public void setForename(Forename aForename) {
		forename = aForename;
	}

	@Override
	public String toString() {
		return getForename() + " " + getSurename() + ", " + getBirthday();
	}
}