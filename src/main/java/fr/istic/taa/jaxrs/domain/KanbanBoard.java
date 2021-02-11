package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Entity
public class KanbanBoard implements Serializable {

    private long id;
    private String nom;
    List<Section> listeSections;
    List<Utilisateur> listeUtilisateurs;

    @XmlElement
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

    @XmlTransient
    @OneToMany(mappedBy = "kanbanBoard", cascade = CascadeType.REMOVE)
    public List<Section> getListeSections() {
        return listeSections;
    }

    public void setListeSections(List<Section> listeSections) {
        this.listeSections = listeSections;
    }

    @XmlTransient
    @ManyToMany(mappedBy = "listeKanbansBoard")
    public List<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
        this.listeUtilisateurs = listeUtilisateurs;
    }
}
