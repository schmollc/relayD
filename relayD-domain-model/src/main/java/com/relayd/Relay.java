package com.relayd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.03.2016
 *
 */
public class Relay implements Serializable {
	private static final long serialVersionUID = 1L;

	UUID uuid = null;
	private Relayname relayname = Relayname.newInstance();
	private RelayEvent relayEvent = RelayEvent.duesseldorf();
	private List<Participant> participants = new ArrayList<Participant>();

	private Relay(RelayEvent aRelayEvent) {
		uuid = UUID.randomUUID();
		relayEvent = aRelayEvent;
		initParticipants();
	}

	private void initParticipants() {
		for (int i = 0; i < RelayEvent.MAX_NUMBER_OF_TRACKS; i++) {
			participants.add(Participant.newInstance());
		}
	}

	public static Relay newInstance() {
		return new Relay(null);
	}

	public static Relay newInstance(RelayEvent relayEvent) {
		return new Relay(relayEvent);
	}

	public RelayEvent getRelayEvent() {
		return relayEvent;
	}

	public void setRelayname(Relayname aRelayname) {
		relayname = aRelayname;
	}

	public Relayname getRelayname() {
		return relayname;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public Participant getParticipantFor(Position position) {
		Participant person = participants.get(position.getValue() - 1);
		return person;
	}

	public void addParticipant(Participant participant, Position position) {
		participants.set(position.getValue() - 1, participant);
	}

	public Integer participantCount() {
		int count = 0;
		for (Participant participant : participants) {
			if (!participant.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	// TODO (Christian, Version 1.4): Wird durch die Lösung mit getEmailList nicht mehr gebraucht! Rückbauen
	public List<Participant> getParticipants() {
		return Collections.unmodifiableList(participants);
	}

	public String getEmailList() {
		StringBuilder builder = new StringBuilder();

		for (Participant each : participants) {
			// TODO (Christian, Version 1.4): Umstellen auf hasMail wie in Person!
			if (!each.getEmail().isEmpty()) {
				builder.append(", " + each.getEmail());
			}
		}
		String output = builder.toString();
		output = output.replaceFirst(", ", "");

		return output;

	}

	public boolean isParticipant(Person aPerson) {
		for (Participant each : participants) {
			if (aPerson.getUuid().equals(each.getUuidPerson())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @deprecated Nur zum testen der Drag&Drop Möglichkeit! Nicht nutzen!
	 */
	@Deprecated
	public void addParticipant(Participant aParticipant) {
		int i = 0;
		for (Participant each : participants) {
			if (each == null) {
				break;
			}
			i++;
		}
		participants.set(i, aParticipant);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Relay other = (Relay) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getRelayname() + " [" + participantCount() + "/" + RelayEvent.MAX_NUMBER_OF_TRACKS + "]";
	}

}
