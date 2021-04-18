package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.dao.generic.FicheDao;
import fr.istic.taa.jaxrs.dao.generic.KandanBoardDao;
import fr.istic.taa.jaxrs.dao.generic.SectionDao;
import fr.istic.taa.jaxrs.dao.generic.UtilisateurDao;
import fr.istic.taa.jaxrs.domain.*;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {

        //Creation de kanbans
        KandanBoardDao kanbanDao = new KandanBoardDao();
        KanbanBoard tab1 = new KanbanBoard();
        tab1.setNom("Premier Kanban");
        kanbanDao.save(tab1);
        KanbanBoard tab2 = new KanbanBoard();
        tab2.setNom("Second Kanban");
        kanbanDao.save(tab2);

        //Creation de sections
        SectionDao sectionDao = new SectionDao();
        Section sec1 = new Section();
        sec1.setNom("A faire");
        sec1.setKanbanBoard(tab1);
        sectionDao.save(sec1);
        Section sec2 = new Section();
        sec2.setNom("En cours");
        sec2.setKanbanBoard(tab1);
        sectionDao.save(sec2);
        Section sec3 = new Section();
        sec3.setNom("Termine");
        sec3.setKanbanBoard(tab1);
        sectionDao.save(sec3);

        //Creation de fiches
        FicheDao ficheDao = new FicheDao();
        Fiche fiche1 = new Fiche();
        fiche1.setLibelle("Premiere fiche");
        fiche1.setSection(sec1);
        ficheDao.save(fiche1);

        //Creation des utilisateurs
        UtilisateurDao utiDao = new UtilisateurDao();
        Utilisateur uti1 = new Utilisateur();
        uti1.setMail("utilisateur1@gmail.com");
        uti1.setNom("Jean");
        List<KanbanBoard> kanbanUti1 = new ArrayList<>();
        kanbanUti1.add(tab1);
        uti1.setListeKanbansBoard(kanbanUti1);
        utiDao.save(uti1);
        Utilisateur uti2 = new Utilisateur();
        uti2.setMail("utilisateur2@gmail.com");
        uti2.setNom("Mi");
        uti2.setListeKanbansBoard(kanbanUti1);
        utiDao.save(uti2);
        Utilisateur uti3 = new Utilisateur();
        uti3.setMail("utilisateur3@gmail.com");
        uti3.setNom("Chel");
        uti3.setListeKanbansBoard(kanbanUti1);
        utiDao.save(uti3);

        //Creation des admins
        Admin admin1 = new Admin();
        admin1.setMail("admin.dev@gmail.com");
        admin1.setNom("Nard");
        admin1.setListeKanbansBoard(kanbanUti1);
        admin1.setEquipe("Developpement");
        utiDao.save(admin1);
        Admin admin2 = new Admin();
        admin2.setMail("admin.bigdata@gmail.com");
        admin2.setNom("Ber");
        admin2.setListeKanbansBoard(kanbanUti1);
        admin2.setEquipe("Big Data");
        utiDao.save(admin2);

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        RestApplication ta = new RestApplication();

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8082, "localhost")
        );

        logger.info("JAX-RS based micro-service running!");
    }
}
