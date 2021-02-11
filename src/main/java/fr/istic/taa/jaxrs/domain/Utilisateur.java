package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role",discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "touslesutilisateurs", query = "select u from Utilisateur u"),
        @NamedQuery(name = "touslesutilisateursPARNOM", query = "select u from Utilisateur u where u.nom=:nom")
        })
public class Utilisateur implements Serializable {

    String mail;
    String nom;
    List<Fiche> listeFiches;
    List<KanbanBoard> listeKanbansBoard;

    @XmlElement
    @Id
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
    public List<Fiche> getListeFiches() {
        return listeFiches;
    }

    public void setListeFiches(List<Fiche> listeFiches) {
        this.listeFiches = listeFiches;
    }

    @XmlElementWrapper
    @XmlElement
    @ManyToMany
    public List<KanbanBoard> getListeKanbansBoard() {
        return listeKanbansBoard;
    }

    public void setListeKanbansBoard(List<KanbanBoard> listeKanbansBoard) {
        this.listeKanbansBoard = listeKanbansBoard;
    }

    public String toString(){
        return "Mail de l'utilisateur : " + getMail();
    }
}
