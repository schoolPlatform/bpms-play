package it.redhat.demo.rest;

import it.redhat.demo.query.QueryProducer;
import it.redhat.demo.query.QuerySelector;
import org.kie.server.api.model.definition.QueryDefinition;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.client.QueryServicesClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 27/03/17.
 */

@Path("queries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdvancedQueryResource {

    @Inject
    private QueryServicesClient queryServices;

    @Inject
    private QuerySelector querySelector;

    @GET
    public List<QueryDefinition> getQueryDefinitions() {

        return queryServices.getQueries(0, 10000);

    }

    @GET
    @Path("{query}")
    public List executeQuery(@PathParam("query") String query) {

        QueryDefinition definition = querySelector.selectQuery(query);

        if (QueryProducer.PROCESS.equals(definition.getTarget())) {
            return queryServices.query(query, query, 0, 10, ProcessInstance.class);
        } else {
            return queryServices.query(query, query, 0, 10, TaskInstance.class);
        }

    }

}
