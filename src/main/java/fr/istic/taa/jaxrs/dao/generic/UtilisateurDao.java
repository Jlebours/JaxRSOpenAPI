package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.KanbanBoard;
import fr.istic.taa.jaxrs.domain.Utilisateur;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UtilisateurDao extends AbstractJpaDao<String, Utilisateur> {

    public UtilisateurDao() {
        super(Utilisateur.class);
    }

    // Méthodes appelées par le service web

    public List<Utilisateur> getUtilisateurs() {
        return entityManager.createNamedQuery("touslesutilisateurs", Utilisateur.class).getResultList();
    }

    public Utilisateur getUtilisateurByMail(String mail) {
        String query = "select u from Utilisateur as u where u.mail=:mail";
        return entityManager.createQuery(query, Utilisateur.class).setParameter("mail", mail).getSingleResult();
    }

    public Utilisateur removeUtilisateurByMail(String mail) {
        String query = "delete from Utilisateur u where u.mail=:mail";
        return entityManager.createQuery(query, Utilisateur.class).setParameter("mail", mail).getSingleResult();
    }

    public List<Utilisateur> getUtilisateursAdmin() {
        return entityManager.createNamedQuery("touslesutilisateursADMIN", Utilisateur.class).getResultList();
    }

    // Tests pour l'avancée du TP

    public Utilisateur getUtilisateurWithMail() {
        String query = "select u from Utilisateur as u where u.mail=:mail";
        return entityManager.createQuery(query, Utilisateur.class)
                .setParameter("mail", "admin@gmail.com").getSingleResult();
    }

    public String getUtilisateurWithMailCriteria(String nom) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Utilisateur> from = query.from(Utilisateur.class);
        query.select(from.get("mail")).where(from.get("nom").in(nom));
        return entityManager.createQuery(query).getSingleResult();
    }

}
