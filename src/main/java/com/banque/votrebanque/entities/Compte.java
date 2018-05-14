package com.banque.votrebanque.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @DiscriminatorColumn(name = "TYPE_CPTE",discriminatorType = DiscriminatorType.STRING,length = 2)
public abstract class Compte implements Serializable {
    @Id
    private String codeCompte;
    private Date DateCreation;
    private Double solde;
    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private Client client;
    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;

    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }

    public Compte(String codeCompte, Date dateCreation, Double solde, Client client) {

        this.codeCompte = codeCompte;
        DateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

    public Compte() {

    }
}
