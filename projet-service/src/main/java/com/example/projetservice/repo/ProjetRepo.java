package com.example.projetservice.repo;

import com.example.projetservice.entity.Condidature;
import com.example.projetservice.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepo extends JpaRepository<Projet,Long> {



}
