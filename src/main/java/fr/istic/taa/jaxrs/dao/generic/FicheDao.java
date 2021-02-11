package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Fiche;

public class FicheDao extends AbstractJpaDao<Long, Fiche> {

    public FicheDao() {
        super(Fiche.class);
    }

}
