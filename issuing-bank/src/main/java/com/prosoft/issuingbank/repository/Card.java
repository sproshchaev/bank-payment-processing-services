package com.prosoft.issuingbank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Card extends JpaRepository<Card, Long> {

}
