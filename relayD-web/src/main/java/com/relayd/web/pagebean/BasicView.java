package com.relayd.web.pagebean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventName;
import com.relayd.client.jaxb.EventDTO;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;
import com.relayd.web.rest.client.DefaultRestGetService;
import com.relayd.web.rest.client.RestGetService;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 * status initial
 * 
 */
@ManagedBean
@ViewScoped
public class BasicView implements Serializable {
	private static final long serialVersionUID = 1L;

	// This will be later set through Inject, Factory or something else....
	private RelayEventGateway gateway = null;

	private Date relayEventDate = null;
	private String relayEventName = null;

	public BasicView() {
		// This will be later set through Inject, Factory or something else....
		gateway = new RelayEventGatewayMemory();
		// Use Gateway you need for your test e.g. File for working without Network
		//		gateway = new RelayEventGatewaySql();
		//		gateway = new RelayEventGatewayFile();
		// etc...
	}

	public List<RelayEvent> getRelayEvents() {
		return gateway.getAll();
	}

	public List<EventDTO> getEvents() {
		return EventDTO.getRandomEvents();
	}

	public String getEventsPingRequest(String uriAuthority) throws URISyntaxException {
		String pathToResource = "/relayD-api/resources/events/ping";
		URI resourceUri = getResourceUri(uriAuthority, pathToResource);

		RestGetService restService = createRestGetService(resourceUri);

		return restService.getResult();
	}

	RestGetService createRestGetService(URI resourceUri) {
		return new DefaultRestGetService.Buillder(resourceUri)
				.build();
	}

	// TODO (Erik, 2016-05-29): This method DOES NOT belong here.
	private URI getResourceUri(String uriAuthority, String pathToResource) throws URISyntaxException {
		// Build URI string as defined in https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Syntax
		String scheme = "http:";
		String uriString = scheme + "//" + uriAuthority + "/" + pathToResource;
		URI resourceUri = new URI(uriString);

		return resourceUri;
	}

	public Date getDate() {
		return relayEventDate;
	}

	public void setDate(Date aDate) {
		relayEventDate = aDate;
	}

	public String getName() {
		return relayEventName;
	}

	public void setName(String aName) {
		relayEventName = aName;
	}

	public void click() {
		EventName eventName = new EventName(getName());
		Date eventDay = getDate();

		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		gateway.set(relayEvent);
	}
}