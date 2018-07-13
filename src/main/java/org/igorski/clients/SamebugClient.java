package org.igorski.clients;

import org.igorski.model.CrashResponse;
import org.igorski.model.SamebugRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/crashes")
public interface SamebugClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    CrashResponse getCrashReport(SamebugRequest user);
}
