package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.TreeNodeRow;

import static org.mockito.Mockito.*;

/**
 * Tests are the Programmer’s stone, transmuting fear into boredom.
 *  - Kent Beck
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayBrowsePageBeanTest {

	@Spy
	@InjectMocks
	private RelayBrowsePageBean sut;

	@Mock
	private RelayEditPageBean relayEditPageBean;

	@Mock
	private RelayBridge relayBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
	}

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testAddPersonToRelay_ForParticipantIsSelectedAndNoPersonIsSelected() {
		ActionEvent dummyActionEvent = null;

		Participant participant = Participant.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(participant, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		sut.setSelectedPersons(null);

		sut.addPersonToRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Please select a Person!");
	}

	@Test
	public void testAddPersonToRelay_ForParticipantIsSelectedAndTwoPersonsAreSelected() {
		ActionEvent dummyActionEvent = null;

		Participant participant = Participant.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(participant, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));

		Person peterShaw = Person.newInstance();
		peterShaw.setForename(Forename.newInstance("Peter"));
		peterShaw.setSurename(Surename.newInstance("Shaw"));

		sut.setSelectedPersons(createListFor(justusJonas, peterShaw));

		sut.addPersonToRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Please select a single Person!");
	}

	@Test
	public void testAddPersonToRelay_ForNoSelection() {
		ActionEvent dummyActionEvent = null;

		sut.addPersonToRelay(dummyActionEvent);

		verify(relayBridge, never()).persist(any(TreeNode.class));
		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Please select a Row!");
	}

	@Test
	public void testAddPersonToRelay_ForParticipantIsSelected() {
		ActionEvent dummyActionEvent = null;

		Participant participant = Participant.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(participant, Position.FIRST));
		sut.setSelectedNode(relayTreeNode);

		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		sut.setSelectedPersons(createListFor(justusJonas));

		sut.addPersonToRelay(dummyActionEvent);

		TreeNodeRow selectedRelayNode = (TreeNodeRow) relayTreeNode.getData();
		Participant actual = selectedRelayNode.getParticipant();

		assertEquals("UUID not correct!", justusJonas.getUuid(), actual.getUuidPerson());
		verify(relayBridge).persist(any(TreeNode.class));
		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
	}

	private List<Person> createListFor(Person... person) {
		List<Person> somePersons = new ArrayList<Person>();
		somePersons.addAll(Arrays.asList(person));
		return somePersons;
	}

	@Test
	public void testAddPersonToRelay_ForRelayIsSelected() {
		ActionEvent dummyActionEvent = null;

		Relay relay = Relay.newInstance();
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay));
		sut.setSelectedNode(relayTreeNode);

		sut.addPersonToRelay(dummyActionEvent);

		verify(relayBridge, never()).persist(any(TreeNode.class));
		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Only for Participant Row possible!");
	}

	@Test
	public void testRemovePersonFromRelay_ForNoRowIsSelected() {
		ActionEvent dummyActionEvent = null;

		sut.removePersonFromRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Please select a Row!");
		verify(relayBridge, never()).persist(any(TreeNode.class));
	}

	@Test
	public void testRemovePersonFromRelay_ForRelayRowIsSelected() {
		ActionEvent dummyActionEvent = null;
		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removePersonFromRelay(dummyActionEvent);

		verify(sut).showMessage(FacesMessage.SEVERITY_ERROR, RelayBrowsePageBean.NOT_POSSIBLE, "Only for Participant Row possible!");
		verify(relayBridge, never()).persist(any(TreeNode.class));
	}

	@Test
	public void testRemovePersonFromRelay_ForParticipantRowIsSelected() {
		ActionEvent dummyActionEvent = null;
		Participant personRelay = Participant.newInstance(Forename.newInstance(), Surename.newInstance(), UUID.randomUUID());
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(personRelay, Position.FIRST), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removePersonFromRelay(dummyActionEvent);

		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
		verify(relayBridge).persist(any(TreeNode.class));
	}

	@Test
	public void testRemovePersonFromRelay_ForRelayParticipantRowIsSelectedAndEmpty() {
		ActionEvent dummyActionEvent = null;
		Participant personRelay = Participant.newInstance(Forename.newInstance(), Surename.newInstance(), null);
		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(personRelay, Position.FIRST), null);
		sut.setSelectedNode(relayTreeNode);

		sut.removePersonFromRelay(dummyActionEvent);

		verify(sut, never()).showMessage(any(FacesMessage.Severity.class), anyString(), anyString());
		verify(relayBridge, never()).persist(any(TreeNode.class));
	}

	@Test
	public void testIsRelayRowSelected_ForNoRowSelected() {
		boolean condition = sut.isRelayRowSelected();

		assertFalse("Relay selected not correct!", condition);
	}

	@Test
	public void testIsRelayRowSelected_ForRowParticipantSelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Participant.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayRowSelected();

		assertFalse("Relay selected not correct!", condition);
	}

	@Test
	public void testIsRelayRowSelected_ForRowRelaySelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isRelayRowSelected();

		assertTrue("Relay selected not correct!", condition);
	}

	@Test
	public void testIsParticipantRowSelected() {
		boolean condition = sut.isParticipantRowSelected();

		assertFalse("Participant selected not corret!", condition);
	}

	@Test
	public void testIsParticipantRowSelected_ForRowParticipantSelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Participant.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isParticipantRowSelected();

		assertTrue("Participant selected not correct!", condition);
	}

	@Test
	public void testIsParticipantRowSelected_ForRowRelaySelected() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);

		boolean condition = sut.isParticipantRowSelected();

		assertFalse("Participant selected not correct!", condition);
	}

	@Test
	public void testEditRow_ForNonSelectedRow() {
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(relayEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEditRow_ForSelectedRowParticipant() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Participant.newInstance(), Position.FIRST));
		sut.setSelectedNode(selectedTreeNode);
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(relayEditPageBean, never()).openDialogFor(any(UUID.class));
		verify(sut).showMessageErrorNoRowRelaySelected();
	}

	@Test
	public void testEditRow_ForSelectedRowRelay() {
		TreeNode selectedTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(Relay.newInstance()));
		sut.setSelectedNode(selectedTreeNode);
		ActionEvent dummyActionEvent = null;

		sut.editRelay(dummyActionEvent);

		verify(relayEditPageBean).openDialogFor(any(UUID.class));
		verify(sut, never()).showMessageErrorNoRowRelaySelected();
	}

}