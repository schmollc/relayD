package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import org.junit.Test;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 22.03.2016
 * status initial
 */
public class PersonTest {

	@Test
	public void testCreateCompletePerson() {
		Person sut = Person.newInstance();
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setForename(Forename.newInstance("Justus"));
		sut.setBirthday(Birthday.newInstance(LocalDate.of(1956, Month.FEBRUARY, 17)));
		sut.setShirtsize(Shirtsize.HerrenM);
		sut.setNationality(Locale.GERMANY);

		String personAsString = sut.toString();

		assertEquals("Justus Jonas, 17-02-1956, HerrenM, Germany", personAsString);
	}

	@Test
	public void testChangeSurename() {
		final Surename FIRST_CHOICE = Surename.newInstance("McFly");
		final Surename SECOND_CHOICE = Surename.newInstance("Kline");

		Person sut = Person.newInstance();
		Surename surename = sut.getSurename();

		assertNull("Erwarte keine gueltige Instanz.", surename);

		sut.setSurename(FIRST_CHOICE);

		surename = sut.getSurename();

		assertNotNull(surename);
		assertEquals("Erwarte den gesetzten Surename.", FIRST_CHOICE, surename);

		sut.setSurename(SECOND_CHOICE);

		surename = sut.getSurename();

		assertNotNull(surename);
		assertEquals("Erwarte den neu gesetzten Surename.", SECOND_CHOICE, surename);
	}

	@Test
	public void testChangeForename() {
		final Forename FIRST_CHOICE = Forename.newInstance("Marty");
		final Forename SECOND_CHOICE = Forename.newInstance("Celvin");

		Person sut = Person.newInstance();
		Forename forename = sut.getForename();

		assertNull("Erwarte keine gueltige Instanz.", forename);

		sut.setForename(FIRST_CHOICE);

		forename = sut.getForename();

		assertNotNull("Erwarte gueltige Instanz.", forename);
		assertEquals("Erwarte den gesetzten Forename.", FIRST_CHOICE, forename);

		sut.setForename(SECOND_CHOICE);

		forename = sut.getForename();

		assertNotNull(forename);
		assertEquals("Erwarte den neu gesetzten Forename.", SECOND_CHOICE, forename);
	}

	@Test
	public void testChangeShirtsize() {
		final Shirtsize HERREN_XXL = Shirtsize.HerrenXXL;
		Person sut = Person.newInstance();

		Shirtsize shirtsize = sut.getShirtsize();

		assertNull("Erwarte keine gueltige Instanz.", shirtsize);

		sut.setShirtsize(HERREN_XXL);

		shirtsize = sut.getShirtsize();
		assertEquals("Shirtsize ist nicht korrekt.", HERREN_XXL, shirtsize);
	}

	@Test
	public void testChangeNationality() {
		final Locale GERMANY = Locale.GERMANY;
		Person sut = Person.newInstance();

		Locale nationality = sut.getNationality();

		assertNull("Erwarte keine gueltige Instanz.", nationality);

		sut.setNationality(GERMANY);

		nationality = sut.getNationality();
		assertEquals("Nationality ist nicht korrekt.", GERMANY, nationality);
	}
}