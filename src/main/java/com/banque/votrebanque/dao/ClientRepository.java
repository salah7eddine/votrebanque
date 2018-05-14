package com.banque.votrebanque.dao;

import com.banque.votrebanque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
