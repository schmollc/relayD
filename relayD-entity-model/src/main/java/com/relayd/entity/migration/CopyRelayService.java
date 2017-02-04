package com.relayd.entity.migration;

import java.util.*;

import com.relayd.entity.*;
import com.relayd.jpa.GenericJpaDao;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  04.02.2017
 *
 */
public class CopyRelayService extends MigrationService {

	private CopyRelayService(GenericJpaDao aJpaDao) {
		setJpaDao(aJpaDao);
	}
	
	public static CopyRelayService newInstance(GenericJpaDao aJpaDao) {
		return new CopyRelayService(aJpaDao);
	}
	
	public RelayCounter copyAllRelayToRelay2() {
		@SuppressWarnings("unchecked")
		List<RelayEntity> relayEntities = (List<RelayEntity>) readRelays(READ_ALL_RELAY_ENTITIES_SQL);
		
		for (RelayEntity eachRelayEntity : relayEntities) {
			Relay2Entity relay2Entity = copyToRelay2Entity(eachRelayEntity);
			getRelayCounter().incrementRelays();
			getRelayCounter().incrementParticipants(relay2Entity.getParticipantEntities().size());
		}
		
		return getRelayCounter();
	}

	Relay2Entity copyToRelay2Entity(RelayEntity eachRelayEntity) {
		Relay2Entity relay2Entity = Relay2Entity.newInstance();
		relay2Entity.setRelayname(eachRelayEntity.getRelayname());
		
		addParticipantAtPositionToRelay2Entity(eachRelayEntity.getParticipantOne(), Integer.valueOf(1), relay2Entity);
		addParticipantAtPositionToRelay2Entity(eachRelayEntity.getParticipantTwo(), Integer.valueOf(2), relay2Entity);
		addParticipantAtPositionToRelay2Entity(eachRelayEntity.getParticipantThree(), Integer.valueOf(3), relay2Entity);
		addParticipantAtPositionToRelay2Entity(eachRelayEntity.getParticipantFour(), Integer.valueOf(4), relay2Entity);
		
		return relay2Entity;
	}
	
	void addParticipantAtPositionToRelay2Entity(UUID personId, Integer aPosition, Relay2Entity aRelay2Entity) {
		if (personId != null) {
			ParticipantEntity participantEntity = ParticipantEntity.newInstance();
			participantEntity.setPosition(aPosition);
			
			PersonEntity personEntity = getJpaDao().findById(PersonEntity.class, personId.toString());
			participantEntity.setPersonEntity(personEntity);
			
			aRelay2Entity.addParticipantEntity(participantEntity);
		}
	}
}
