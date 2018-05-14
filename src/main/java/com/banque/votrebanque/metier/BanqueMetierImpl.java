package com.banque.votrebanque.metier;

import com.banque.votrebanque.dao.CompteRepository;
import com.banque.votrebanque.dao.OperationRepository;
import com.banque.votrebanque.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;


    @Override
    public Compte consulterCompte(String codeCpte) {
        Compte cp=compteRepository.findOne(codeCpte);
        if(cp==null) throw new RuntimeException("Compte Introuvable!");
        return cp;
    }

    @Override
    public void verser(String codeCpte, double montant) {
        Compte cp=consulterCompte(codeCpte);
        Versement v=new Versement(new Date(),montant,cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);

    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp=consulterCompte(codeCpte);
        double facilitiesCaisse=0;
        if(cp instanceof CompteCourant){
            facilitiesCaisse=((CompteCourant) cp).getDecouvert();
        }
        if(cp.getSolde()+facilitiesCaisse<montant){
            throw new RuntimeException("Solde Insuffisant");
        }
        Retrait r=new Retrait(new Date(),montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()- montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpt1, String codeCpt2, double montant) {
        if(codeCpt1.equals(codeCpt2)) throw new RuntimeException("Impossible le virement sur le meme compte");
        retirer(codeCpt1,montant);
        verser(codeCpt2,montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {
        return operationRepository.listOperation
                (codeCpte,new PageRequest(page,size));
    }
}
