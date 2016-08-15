package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import com.relayd.Person;

/**
 * This Bridge could handle the REST - Serivce
 * Actual its only a layer for the PersonGateway.
 *
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
public interface PersonBridge {

	public List<Person> all();

	public void update(Person person);

	public void create(Person person);

	public Person get(UUID uuid);

	public void remove(Person person);
}