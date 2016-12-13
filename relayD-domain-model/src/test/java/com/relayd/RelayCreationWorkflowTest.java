package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Langweilige Aktivitäten sind perverserweise längst nicht so langweilig, wenn man sich intensiv auf sie konzentriert.
 *  - David Foster Wallace
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayCreationWorkflowTest {

	@Test
	public void testCreateRelayAndAddToEvent() {
		// Event wird "angelegt" bzw ist vorhanden.
		RelayEvent duesseldorf = RelayEvent.duesseldorf();

		// Macht das Anlegen einer Relay ohne ein Event überhaupt Sinn?
		// Sollte die newInstance nicht als Parameter ein Event besitzen?
		Relay staubwolke = Relay.newInstance(duesseldorf);

		Relayname relayname = Relayname.newInstance("Staubwolke");
		staubwolke.setRelayname(relayname);

		// Wenn im Constructor das Event angeben wurde ist folgendes nicht mehr nötig/möglich
		//		duesseldorf.addRelay(staubwolke);

		// Nun kann schon ein persistieren erfolgen?
		// Oder sollte jede Relay immer min. eine Person besitzen?

		Participant justusJonas = Participant.newInstance(createPersonFor(Forename.newInstance("Justus"), Surename.newInstance("Jonas"), UUID.randomUUID()));

		staubwolke.addParticipant(justusJonas, Position.FIRST);

		// Prüfungen
		Participant peterShaw = Participant.newInstance(createPersonFor(Forename.newInstance("Peter"), Surename.newInstance("Shaw"), UUID.randomUUID()));

		// Sollte ein Fehler verursachen, da Position 1 schon vergeben!
		staubwolke.addParticipant(peterShaw, Position.FIRST);

		Relay dieVierFragezeichen = Relay.newInstance(duesseldorf);
		dieVierFragezeichen.setRelayname(Relayname.newInstance("Die 4 ????"));

		// KEIN Fehler! Aber vielleicht eine Warnung?
		// (Theoretisch kann EINE Person ALLE 4 Strecken für ALLE Staffeln laufen! -> Shirtsize XXL!)
		dieVierFragezeichen.addParticipant(justusJonas, Position.FIRST);

		// Bedenkliche Konstellation
		Participant bobAndrews = Participant.newInstance(createPersonFor(Forename.newInstance("Bob"), Surename.newInstance("Andrews"), UUID.randomUUID()));

		// Bob springt ein in Staffel Staubwolke die 1. Strecke mit z.B. 56:33min
		// und möchte in Staffel 4???? die zweite Strecke laufen.
		// Peter Shaw läuft aber in Staffel 4???? die erste Etappe und zwar mit 44:12
		// D.h. Bob ist noch gar nicht in der Wechselzone für die 2. Etappe.
		// Bei untigem Code sollte dann ggf eine Warnung erscheinen?
		// Die Konstellation würde nur "sicher" sein wenn Bob auch die ERSTE Position in
		// der Staffel 4???? ist.
		staubwolke.addParticipant(bobAndrews, Position.FIRST);
		dieVierFragezeichen.addParticipant(bobAndrews, Position.SECOND);

		// Justus jonas fällt aus
		//		staubwolke.remove(Position.FIRST);
		// oder
		//		staubwolke.remove(justusJonas);

		// Staubwolke fällt komplett aus....
		// -> löschen aus Event?
		//		duesseldorf.remove(staubwolke);
		// oder recyclen?
		//		staubwolke.setRelayname(Relayname.newInstance("Anderer Relay Name"));

		//Statistiken
		duesseldorf.getMaxNumberOfRelays();
		duesseldorf.getNumberOfRelays();
		//		duesseldorf.getEmptyRelays();
		// Name der Methode noch etwas schwammig
		//		duesseldorf.getRelaysWithMissingData();

		// Workflow
		// Justus Jonas sagt komplett ab -> krank (Beispiel: Felix Woertelboer)
		//		justusJonas.setAvailableForEvent(duesseldorf, false);
		// oder
		//		justusJonas.setNotAvailableForEvent(duesseldorf);
		// dann sollte er automatisch aus den Relays entfernt werden in welchen er teilnimmt

	}

	@Test
	@Ignore("Relay befindet sich in der Umstellung. Dieser Test scheitert nun!")
	public void testShowcaseRelationTrackToParticipantIsWrong() {
		Eventname eventName = Eventname.newInstance("Boston Marathon");
		EventDay eventDay = EventDay.newInstance(LocalDate.now());
		RelayEvent boston = RelayEvent.newInstance(eventName, eventDay);
		Track firstTrack = boston.getTrackForPosition(Position.FIRST);

		assertNull("The person for the first track is not correct!", firstTrack.getParticipantRelay());

		Relay slowMovers = Relay.newInstance(boston);
		Participant mike = Participant.newInstance(createPersonFor(Forename.newInstance("Mike"), Surename.newInstance(), UUID.randomUUID()));
		slowMovers.addParticipant(mike, Position.FIRST);

		Participant actual = firstTrack.getParticipantRelay();
		assertEquals("Person at first position of Track is not Mike!", mike, actual);

		Relay dillyDallies = Relay.newInstance(boston);
		Participant sarah = Participant.newInstance(createPersonFor(Forename.newInstance("Sarah"), Surename.newInstance(), UUID.randomUUID()));
		dillyDallies.addParticipant(sarah, Position.FIRST);

		actual = firstTrack.getParticipantRelay();
		assertEquals("Person at first position of Track is not Sarah!", sarah, actual);
		// But what about Mike? He lost his track position to Sarah!
	}

	private Person createPersonFor(Forename aForename, Surename aSurename, UUID anUuid) {
		Person person = Person.newInstance();
		person.setUuid(anUuid);
		person.setForename(aForename);
		person.setSurename(aSurename);
		return person;
	}
}