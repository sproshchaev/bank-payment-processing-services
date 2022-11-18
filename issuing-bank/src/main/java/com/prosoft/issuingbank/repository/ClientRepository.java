package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select c from Client c where (c.lastName = :lastName) and (c.firstName = :firstName) " +
            "and (c.middleName = :middleName) and (c.birthDate = :birthDate) and (c.document = :document)")
    List<Client> getClientByPersonalData(@Param("lastName") String lastName,
                                         @Param("firstName") String firstName,
                                         @Param("middleName") String middleName,
                                         @Param("birthDate") Date birthDate,
                                         @Param("document") String document);
}
