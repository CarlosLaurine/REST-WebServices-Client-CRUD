package com.carloslaurinedev.clientrestwebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carloslaurinedev.clientrestwebservices.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
