package org.radar.services;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.radar.analysis.reporter.Reporter;

@Path("/reporte")
@RequestScoped
public class ReportRestService {

    private static Reporter reporter = null;

    @GET
    @Path("/getReporte")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporte(@PathParam("nombrePaquete") String nombrePaquete) {
        reporter = new Reporter("com");
        return Response.status(200).entity(reporter.createRationaleReportRestByOne()).build();
    }

}
