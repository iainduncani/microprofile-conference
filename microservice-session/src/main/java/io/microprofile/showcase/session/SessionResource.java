/*
 * Copyright 2016 Microprofile.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microprofile.showcase.session;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.microprofile.showcase.bootstrap.BootstrapData;
import io.microprofile.showcase.bootstrap.Session;

/**
 * @author Ken Finnigan
 */
@Path("sessions")
@ApplicationScoped
public class SessionResource {

    @Inject
    private BootstrapData bootstrapData;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Session> allSessions() throws Exception {
        return bootstrapData.getSessions();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Session createSession(Session session) throws Exception {
        return null;
    }

    @GET
    @Path("/{sessionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSession(@PathParam("sessionId") Integer sessionId) throws Exception {
        Optional<Session> result = bootstrapData.getSessions().stream()
            .filter(s -> s.getId() == sessionId)
            .findFirst();

        if(result.isPresent())
            return Response.ok(result.get()).build();
        else
           return Response.status(404).build();

    }

    @PUT
    @Path("/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Session updateSession(@PathParam("sessionId") Integer sessionId, Session session) throws Exception {
        return null;
    }

    @DELETE
    @Path("/{sessionId}")
    public void deleteSession(@PathParam("sessionId") Integer sessionId) throws Exception {
    }

    //TODO Add Search

    @GET
    @Path("/{sessionId}/speakers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sessionSpeakers(@PathParam("sessionId") Integer sessionId) throws Exception {

        Optional<Session> session = bootstrapData.getSessions().stream()
            .filter(s -> s.getId() == sessionId)
            .findFirst();

        if(session.isPresent())
            return Response.ok(session.get().getSpeakers()).build();
        else
            return Response.status(404).build();

    }

    @PUT
    @Path("/{sessionId}/speakers/{speakerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Session addSessionSpeaker(@PathParam("sessionId") Integer sessionId, @PathParam("speakerId") Integer speakerId) throws Exception {
        return null;
    }

    @DELETE
    @Path("/{sessionId}/speakers/{speakerId}")
    public void removeSessionSpeaker(@PathParam("sessionId") Integer sessionId, @PathParam("speakerId") Integer speakerId) throws Exception {
    }


}
