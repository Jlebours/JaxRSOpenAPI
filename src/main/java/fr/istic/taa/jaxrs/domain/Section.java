package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Entity
public class Section implements Serializable {

    long id;
    String nom;
    List<Fiche> listeFiches;
    KanbanBoard kanbanBoard;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElementWrapper
    @XmlElement
    @OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST)
    public List<Fiche> getListeFiches() {
        return listeFiches;
    }

    public void setListeFiches(List<Fiche> listeFiches) {
        this.listeFiches = listeFiches;
    }

    @XmlElement
    @ManyToOne
    public KanbanBoard getKanbanBoard() {
        return kanbanBoard;
    }

    public void setKanbanBoard(KanbanBoard kanbanBoard) {
        this.kanbanBoard = kanbanBoard;
    }
}
