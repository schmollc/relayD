package com.relayd.entity;

import java.util.UUID;

import javax.persistence.*;

import org.apache.openjpa.persistence.jdbc.ForeignKey;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  14.12.2016
 *
 */
@Entity
@Table(name="participant")
public class ParticipantEntity {
	
	@Id
	@Column(length=36)
	private String id;
	
	@Column(name="relayPosition", nullable=false)
	private Integer position;

	@ManyToOne
	@Column(name="personId", nullable=false, length=36)
	@ForeignKey
	private PersonEntity personEntity;

	@ManyToOne
	@Column(name="relay2Id", nullable=false, length=36)
	@ForeignKey
	private Relay2Entity relay2Entity;

	public static ParticipantEntity newInstance(String anId) {
		// TODO EL (2017-01-07): Introduce a class to handle strings that should represent a UUID.
		if (anId == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}
		try {
			UUID.fromString(anId);
		} catch (IllegalArgumentException iAEx) {
			throw new IllegalArgumentException("[anId] is not a valid representation of an UUID.");
		}
		ParticipantEntity participantEntity = new ParticipantEntity();
		participantEntity.setId(anId);
		
		return participantEntity;
	}

	public static ParticipantEntity newInstance() {
		return ParticipantEntity.newInstance(UUID.randomUUID().toString());
	}
	
	private void setId(String anId) {
		id = anId;
	}

	public String getId() {
		return id;
	}

	public void setPosition(Integer aPosition) {
		position = aPosition;
	}

	public Integer getPosition() {
		return position;
	}
	
	public void setPersonEntity(PersonEntity aPersonEntity) {
		personEntity = aPersonEntity;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	void setRelay2Entity(Relay2Entity aRelay2Entity) {
		relay2Entity = aRelay2Entity;
	}
	
	public Relay2Entity getRelay2Entity() {
		return relay2Entity;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipantEntity other = (ParticipantEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
