package com.example.Beehive.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Beehive.Backend.model.Information;


@Repository
public interface InformationRepo extends JpaRepository<Information,Long>{

}
