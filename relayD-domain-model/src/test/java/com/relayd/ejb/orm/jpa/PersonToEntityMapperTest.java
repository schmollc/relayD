package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
 * The value of layers is that each specializes in a particular aspect of a computer program.
 * 		- Eric Evans (Domain-Driven Design) -
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonToEntityMapperTest {

	private PersonToEntityMapper sut = PersonToEntityMapper.newInstance();
	private Person person = Person.newInstance();
	private PersonEntity personEntity = PersonEntity.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapPersonToEntity_whenPersonIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[person] must not be 'null'!");

		sut.mapPersonToEntity(null, personEntity);
	}

	@Test
	public void testMapPersonToEntity_whenPersonEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[personEntity] must not be 'null'!");

		sut.mapPersonToEntity(person, null);
	}

	@Test
	public void testMapPersonToEntity_id() {
		PersonEntity personEntity = PersonEntity.newInstance(person.getUuid());
		String expected = person.getUuid().toString();

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_forename() {
		String expected = "Steve";
		person.setForename(Forename.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getForename();
		assertEquals("Mapping of [forename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_surename() {
		String expected = "Jobs";
		person.setSurename(Surename.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getSurename();
		assertEquals("Mapping of [surename] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_email() {
		String expected = Email.createFromLocalAndDomainPart("steve.jobs", "apple.com").toString();
		person.setEmail(Email.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getEmail();
		assertEquals("Mapping of [email] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_yearOfBirth() {
		Integer expected = Integer.valueOf(1965);
		person.setYearOfBirth(YearOfBirth.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		Integer actual = personEntity.getYearOfBirth();
		assertEquals("Mapping of [yearOfBirth] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_comment() {
		String expected = "Just a remark";
		person.setComment(Comment.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		String actual = personEntity.getComment();
		assertEquals("Mapping of [comment] is not correct!", expected, actual);
	}

	@Test
	public void testMapPersonToEntity_shirtsize() {
		Integer expected = Shirtsize.HerrenL.getSize();
		person.setShirtsize(Shirtsize.newInstance(expected));

		sut.mapPersonToEntity(person, personEntity);

		Integer actual = personEntity.getShirtsize();
		assertEquals("Mapping of [shirtsize] is not correct!", expected, actual);
	}
}
