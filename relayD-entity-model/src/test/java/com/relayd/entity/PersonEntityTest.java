package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since  09.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonEntityTest {

	@Test
	public void testConstructor() {
		PersonEntity sut = new PersonEntity();
		assertNull("[id] not correct!", sut.getId());
		assertNull("[forename] not correct!", sut.getForename());
		assertNull("[surename] not correct!", sut.getSurename());
		assertNull("[emailadress] not correct!", sut.getEmail());
		assertNull("[yearOfBirth] not correct!", sut.getYearOfBirth());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[comment] not correct!", sut.getComment());
	}

	@Test
	public void testNewInstance() {
		PersonEntity sut = PersonEntity.newInstance();
		
		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstanceForUuid() {
		UUID expected = UUID.randomUUID();
		PersonEntity sut = PersonEntity.newInstance(expected);
		
		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewInstanceForUuid_withNullValue() {
		@SuppressWarnings("unused")
		PersonEntity sut = PersonEntity.newInstance(null);
	}
	
	@Test
	public void testInstanceIsCreatedWithoutAnyFurtherInformation() {
		PersonEntity sut = new PersonEntity.Builder().build();
		assertNotNull("Id of PersonEntity must not be 'null' after creation.", sut.getId());
		assertNull("[forename] not correct!", sut.getForename());
		assertNull("[surename] not correct!", sut.getSurename());
		assertNull("[emailadress] not correct!", sut.getEmail());
		assertNull("[yearOfBirth] not correct!", sut.getYearOfBirth());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[relayname] not correct!", sut.getRelayname());
		assertNull("[pos] not correct!", sut.getPos());
		assertNull("[shirtsize] not correct!", sut.getShirtsize());
		assertNull("[nationality] not correct!", sut.getNationality());
		assertNull("[comment] not correct!", sut.getComment());
	}

	@Test
	public void testInstanceCreated_ForForename() {
		String forename = "Justus";
		PersonEntity sut = new PersonEntity.Builder().withForename(forename).build();
		assertEquals("[forename] has not been set correctly.", forename, sut.getForename());
	}

	@Test
	public void testInstanceCreated_ForSurename() {
		String surename = "Jonas";
		PersonEntity sut = new PersonEntity.Builder().withSurename(surename).build();
		assertEquals("[surename] has not been set correctly.", surename, sut.getSurename());
	}

	@Test
	public void testInstanceCreated_ForEmail() {
		String email = "Jonas.Jonas@RockyBeach.com";
		PersonEntity sut = new PersonEntity.Builder().withEmail(email).build();
		assertEquals("[email] has not been set correctly.", email, sut.getEmail());
	}

	@Test
	public void testInstanceCreated_ForYearOfBirth() {
		Integer year = 1971;
		PersonEntity sut = new PersonEntity.Builder().withYearOfBirth(year).build();
		assertEquals("[yearOfBirth] has not been set correctly.", year, sut.getYearOfBirth());
	}

	@Test
	public void testInstanceCreated_ForShirtsize() {
		Integer shirtsize = 1;
		PersonEntity sut = new PersonEntity.Builder().withShirtsize(shirtsize).build();
		assertEquals("[shirtsize] has not been set correctly.", shirtsize, sut.getShirtsize());
	}

	@Test
	public void testInstanceCreated_ForNationality() {
		String nationality = "DE";
		PersonEntity sut = new PersonEntity.Builder().withNationality(nationality).build();
		assertEquals("[nationality] has not been set correctly.", nationality, sut.getNationality());
	}

	@Test
	public void testInstanceCreated_ForRelayname() {
		String relayname = "Die 4 ????";
		PersonEntity sut = new PersonEntity.Builder().withRelayname(relayname).build();
		assertEquals("[relayname] has not been set correctly.", relayname, sut.getRelayname());
	}

	@Test
	public void testInstanceCreated_ForPos() {
		Integer position = 1;
		PersonEntity sut = new PersonEntity.Builder().withPos(position).build();
		assertEquals("[pos] has not been set correctly.", position, sut.getPos());
	}

	@Test
	public void testInstanceCreated_ForInfo() {
		String comment = "a info";
		PersonEntity sut = new PersonEntity.Builder().withComment(comment).build();
		assertEquals("[comment] has not been set correctly.", comment, sut.getComment());
	}
	
	@Test
	public void testToString() {
		String forename = "Kent";
		String surename = "Beck";
		PersonEntity sut = new PersonEntity.Builder().withForename(forename).withSurename(surename).build();
		
		String expectedResult = "PersonEntity [id=" + sut.getId() + ", surename=" + surename + ", forename=" + forename + "]";
		String actualResult = sut.toString();
		
		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}
	
	@Test
	public void testEquals_true() {
		String someId = UUID.randomUUID().toString();
		PersonEntity christian = new PersonEntity.Builder().withForename("Christian").withId(someId).build();
		PersonEntity erik = new PersonEntity.Builder().withForename("Erik").withId(someId).build();
		
		assertEquals("Equality has not been tested correctly!", christian, erik);
	}
	
	@Test
	public void testEquals_false() {
		PersonEntity christian = new PersonEntity.Builder().withForename("Christian").build();
		PersonEntity erik = new PersonEntity.Builder().withForename("Erik").build();
		
		assertNotEquals("Equality has not been tested correctly!", christian, erik);
	}
}
