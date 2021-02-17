package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.KandanBoardDao;
import fr.istic.taa.jaxrs.domain.KanbanBoard;
import fr.istic.taa.jaxrs.domain.Pet;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/kanban")
@Produces({"application/json", "application/xml"})
public class KanbanBoardResource {

    @GET
    @Path("/{kanbanId}")
    public KanbanBoard getKanbanById(@PathParam("kanbanId") Long kanbanId) {
        KandanBoardDao kanbanDao = new KandanBoardDao();
        return kanbanDao.getKanbanBoardById(kanbanId);
    }

    @GET
    @Path("/")
    public List<KanbanBoard> getKanban() {
        KandanBoardDao kanbanDao = new KandanBoardDao();
        return kanbanDao.getKanbanBoards();
    }

    /* exemple de json à ajouter pour kanban
    {
        "nom":"Troisième Kanban"
    }
    */

    @POST
    @Consumes("application/json")
    public Response addKanban(
            @Parameter(description = "Kanban object that needs to be added", required = true) KanbanBoard kanban) {
        KandanBoardDao kanbanDao = new KandanBoardDao();
        kanbanDao.save(kanban);
        return Response.ok().entity("SUCCESS").build();
    }
}
