package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.KanbanBoard;

import java.util.List;

public class KandanBoardDao extends AbstractJpaDao<Long, KanbanBoard> {

    public KandanBoardDao() {
        super(KanbanBoard.class);
    }

    public List<KanbanBoard> getKanbanBoards(){
        String query = "select k from KanbanBoard as k";
        return entityManager.createQuery(query, KanbanBoard.class).getResultList();
    }

    public KanbanBoard getKanbanBoardById(Long id){
        String query = "select k from KanbanBoard as k where k.id = :id";
        return entityManager.createQuery(query, KanbanBoard.class).setParameter("id", id).getSingleResult();
    }

}
