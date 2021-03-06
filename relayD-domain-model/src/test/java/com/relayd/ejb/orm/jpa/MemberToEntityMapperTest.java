package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Member;
import com.relayd.entity.MemberEntity;

/**
 * Die Güte des Werkes ist nicht abhängig vom Werkzeug, sondern von demjenigen, der das Werkzeug bedient.
 *  - Unbekannt
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   12.06.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberToEntityMapperTest {
	private MemberToEntityMapper sut = MemberToEntityMapper.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapMemberToEntity_whenMemberIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		MemberEntity dummyMemberEntity = null;

		sut.mapMemberToEntity(null, dummyMemberEntity);
	}

	@Test
	public void testMapMemberToEntity_whenParticipantIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		Member dummyMember = Member.newInstance();

		sut.mapMemberToEntity(dummyMember, null);
	}

	@Test
	public void testMapMemberToEntity_duration() {
		Member member = Member.newInstance();
		Long expected = 15000L;
		member.setDuration(Duration.of(expected, ChronoUnit.MILLIS));

		MemberEntity memberEntity = MemberEntity.newInstance();

		sut.mapMemberToEntity(member, memberEntity);

		Long actual = memberEntity.getDuration();

		assertEquals("Mapping of [duration] is not correct!", expected, actual);
	}
}