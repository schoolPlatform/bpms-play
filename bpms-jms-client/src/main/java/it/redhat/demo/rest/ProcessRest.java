package it.redhat.demo.rest;

import it.redhat.demo.stateless.CustomProcessStateless;
import it.redhat.demo.stateless.ProcessStateless;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by fabio.ercoli@redhat.com on 18/04/17.
 */

@Path("")
public class ProcessRest {

    @Inject
    private ProcessStateless service;

    @Inject
    private CustomProcessStateless custom;

    @GET
    public String ciao() {

        return "ciao";

    }

    @GET
    @Path("test")
    public Long test() {

        return service.startProcess();

    }

    @GET
    @Path("custom")
    @Produces("application/json")
    public String custom() {

        return custom.startProcess();

    }


}
