package com.relayd.web.bridge;

import java.io.Serializable;

import com.relayd.PersonRelay;
import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
 * Klasse übernommen aus dem Primefaces-Beispiel.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 */
public class TreeNodeRow implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonRelay participant;
	private Relay relay; // TODO mit Erik sprechen. Eine Relay hat auch ein NOP?

	public TreeNodeRow(PersonRelay aParticipant) {
		participant = aParticipant;
	}

	public TreeNodeRow(Relay aRelay) {
		relay = aRelay;
	}

	public static TreeNodeRow newInstance(PersonRelay personRelay) {
		return new TreeNodeRow(personRelay);
	}

	public static TreeNodeRow newInstance(Relay relay) {
		return new TreeNodeRow(relay);
	}

	public PersonRelay getParticipant() {
		return participant;
	}

	public void setParticipant(PersonRelay aParticipant) {
		participant = aParticipant;
	}

	public Relay getRelay() {
		return relay;
	}

	public boolean isRelay() {
		return relay != null;
	}

	public Relayname getRelayname() {
		if (relay == null) {
			return Relayname.newInstance();
		}
		return relay.getRelayname();
	}
}