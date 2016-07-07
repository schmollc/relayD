package com.relayd;

import java.util.ArrayList;
import java.util.UUID;

import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Eine Staffel ist ja eigentlich nichts anderes als eine Liste von Personen
 * (oder Etappen) mit einem Namen.<p/>
 *
 * Vorteil: Es müssen nun solche Methoden wie isEmpty(), add(), remove() usw nicht
 * mehr auf funktionalität getestet werden.<br/>
 * Es reicht den Fokus auf die fachlichen Beschränkungen zu legen wie z.B. das erreichen der Max-Grenze
 * (in diesem Fall 4)
 *
 * @author  schmollc (Christian@relayD.de)
 * @since   23.03.2016
 * status   initial
 *
 * TODO -schmollc- Correct messages to english language
 */
public class Relay extends ArrayList<Person> {

	private static final long serialVersionUID = -1655301147830819436L;

	private static final int MAX_MEMBER = 4;

	private UUID uuid = null;
	private Relayname relayname = null;

	private Relay() {
		uuid = UUID.randomUUID();
	}

	public static Relay newInstance() {
		return new Relay();
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public void addPerson(Person person) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("Nicht mehr als " + MAX_MEMBER + " Teilnehmer moeglich.");
		}
		add(person);
	}

	public boolean isFull() {
		return size() == MAX_MEMBER;
	}

	public Person getPerson(Surename surename) {
		for (Person person : this) {
			if (person.getSurename().equals(surename)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Relay: " + getRelayname();
	}

	public UUID getUUID() {
		return uuid;
	}
}