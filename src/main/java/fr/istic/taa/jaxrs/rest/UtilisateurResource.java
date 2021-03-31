package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.KandanBoardDao;
import fr.istic.taa.jaxrs.dao.generic.UtilisateurDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.KanbanBoard;
import fr.istic.taa.jaxrs.domain.Utilisateur;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/utilisateur")
@Produces({"application/json", "application/xml"})
public class UtilisateurResource {

    @GET
    @Path("/")
    public List<Utilisateur> getUtilisateurs() {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        return utilisateurDao.getUtilisateurs();
    }

    @GET
    @Path("/{utilisateurMail}")
    public Utilisateur getUtilisateurByMail(@PathParam("utilisateurMail") String utilisateurMail) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        return utilisateurDao.getUtilisateurByMail(utilisateurMail);
    }

    @DELETE
    @Path("/{utilisateurMail}")
    public Response deleteUtilisateurByMail(@PathParam("utilisateurMail") String utilisateurMail) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.delete(utilisateurDao.getUtilisateurByMail(utilisateurMail));
        return Response.ok().entity("SUCCESS").build();
    }

    @GET
    @Path("/admin")
    public List<Utilisateur> getUtilisateursAdmin() {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        return utilisateurDao.getUtilisateursAdmin();
    }

    @PUT
    @Consumes("application/json")
    public Response modifyUtilisateur(
            @Parameter(description = "Utilisateur object that needs to be added", required = true) Utilisateur utilisateur) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.update(utilisateur);
        return Response.ok().entity("SUCCESS").build();
    }

    @POST
    @Consumes("application/json")
    public Response addUtilisateur(
            @Parameter(description = "Utilisateur object that needs to be added", required = true) Utilisateur utilisateur) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.save(utilisateur);
        return Response.ok().entity("SUCCESS").build();
    }

    @POST
    @Path("/admin")
    @Consumes("application/json")
    public Response addUtilisateurAdmin(
            @Parameter(description = "Utilisateur object that needs to be added", required = true) Admin utilisateur) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.save(utilisateur);
        return Response.ok().entity("SUCCESS").build();
    }

}
