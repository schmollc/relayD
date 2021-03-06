package com.relayd.client.jaxb;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
public class PersonsDTOTest {
	private PersonsDTO sut = new PersonsDTO();

	@Test
	public void testGetPersons() {
		List<PersonDTO> result = sut.getPersons();

		assertTrue("Initial content of 'persons' is not correct!", result.isEmpty());
	}

	@Test
	public void testAddAllPersons() {
		List<PersonDTO> expected = new ArrayList<>();
		expected.add(PersonDTO.newInstance());
		expected.add(PersonDTO.newInstance());
		
		sut.addAllPersons(expected);
		
		List<PersonDTO> actual = sut.getPersons();
		assertEquals("Content of 'persons' is not correct!", expected, actual);
	}
	
	@Test
	public void testToString() {
		String expected = "PersonsDTO [personDTO elements=0]";

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}
}
