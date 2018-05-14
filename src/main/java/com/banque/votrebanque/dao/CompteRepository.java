package com.banque.votrebanque.dao;

import com.banque.votrebanque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,String> {
}
