package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Section;

public class SectionDao extends AbstractJpaDao<Long, Section> {

    public SectionDao() {
        super(Section.class);
    }
}
