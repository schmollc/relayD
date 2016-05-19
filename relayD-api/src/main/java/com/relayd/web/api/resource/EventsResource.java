package com.relayd.web.api.resource;

import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.relayd.client.jaxb.EventDTO;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 27.03.2016
 * status initial
 */
@Path("events")
public class EventsResource {

    @GET
    @Produces("application/json")
    public List<EventDTO> getEvents() {
        // Action here:
        // Retrieve all resources and return.
        return EventDTO.getRandomEvents();
    }

    @POST
    @Consumes("application/json")
    public Response addEvent(EventDTO anEvent, @Context UriInfo uriInfo) {
        // Action here:
        // Validate and persist new resource.

        // TODO Erik, 2016-03-31: discuss the level of input validation here
        URI newEventUri = null;
        try {
            // TODO Erik, 2016-03-31: Find out whether there is away to explicitely avoid the path separator.
            newEventUri = new URI(uriInfo.getAbsolutePath().toString() + "/" + anEvent.getId());
        } catch (URISyntaxException ex) {
            Logger.getLogger(EventsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(201)
                .location(newEventUri)
                .build();
    }
    
    @Path("{id}")
    @PUT
    @Consumes("application/json")
    public Response updateEvent(@PathParam("id") String id, EventDTO anEvent) {
        // Action here:
        // Determine existing resource and persist new state of resource.
        
        // Other possible responses:
        // - 404 not found
        // - 304 not modified (state has not been changed)
        return Response.status(200).build();
    }
    
    @Path("ping")
    @GET
    @Produces("text/plain")
    public String ping() {
        return "Pong response from class " + getClass().getSimpleName() + ".";
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public EventDTO getEvent(@PathParam("id") String id) {
        // Action here:
        // Obtain corresponding resource ('id') and return.
        EventDTO event = EventDTO.getRandomEvents().get(0);
        event.setId(id);
        
        return event;
    }

    @Path("{id}")
    @DELETE
    public Response deleteEvent(@PathParam("id") String id) {
        // Action here:
        // Determine whether 'id' exists and delete the corresponding resource.
        Response.ResponseBuilder responseBuilder;
        if (EventDTO.isEventExistingFor(id)) {
            responseBuilder = Response.status(204);
        } else {
            responseBuilder = Response.status(404);
        }
        
        return responseBuilder.build();
    }
}