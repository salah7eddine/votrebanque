package com.banque.votrebanque.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
    public Versement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }

    public Versement() {
    }
}
