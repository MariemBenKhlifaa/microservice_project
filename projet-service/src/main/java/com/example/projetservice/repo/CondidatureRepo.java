package com.example.projetservice.repo;

import com.example.projetservice.entity.Condidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CondidatureRepo extends JpaRepository<Condidature,Long> {


}
