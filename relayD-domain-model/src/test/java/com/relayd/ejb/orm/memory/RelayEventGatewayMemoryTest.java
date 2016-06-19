package com.relayd.ejb.orm.memory;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   31.05.2016
 * status   initial
 */
public class RelayEventGatewayMemoryTest {
	private RelayEventGatewayMemory sut = new RelayEventGatewayMemory();

	private static final String KÖLN_MARATHON = "Köln Marathon";
	private static final EventDay KÖLN_DAY = new EventDay(LocalDate.of(2017, Calendar.OCTOBER, 4));

	private static final String DÜSSELDORF_MARATHON = "Düsseldorf Marathon";
	private static final EventDay DÜSSELDORF_DAY = new EventDay(LocalDate.of(2017, Calendar.APRIL, 30));

	@Before
	public void setUp() {
		RelayEventGatewayMemory.events.clear();
	}

	@Test
	public void testSet() {
		sut.set(createEventForDuesseldorfMarathon());

		assertFalse(RelayEventGatewayMemory.events.isEmpty());
		assertEquals(1, RelayEventGatewayMemory.events.size());
	}

	@Test
	public void testGetForExistingEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		sut.set(duesseldorfMarathon);
		sut.set(createEventForKoelnMarathon());

		RelayEvent result = sut.get(duesseldorfMarathon.getUuid());

		assertEquals(duesseldorfMarathon.getName(), result.getName());
	}

	@Test
	public void testGetForNonEntry() {
		RelayEvent duesseldorfMarathon = createEventForDuesseldorfMarathon();
		sut.set(duesseldorfMarathon);
		sut.set(createEventForKoelnMarathon());

		RelayEvent result = sut.get(UUID.randomUUID());

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		sut.set(createEventForDuesseldorfMarathon());
		sut.set(createEventForKoelnMarathon());

		List<RelayEvent> result = sut.getAll();
		assertEquals(2, result.size());
	}

	private RelayEvent createEventForDuesseldorfMarathon() {
		EventName eventName = new EventName(DÜSSELDORF_MARATHON);
		EventDay eventDay = DÜSSELDORF_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}

	private RelayEvent createEventForKoelnMarathon() {
		EventName eventName = new EventName(KÖLN_MARATHON);
		EventDay eventDay = KÖLN_DAY;
		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		return relayEvent;
	}
}