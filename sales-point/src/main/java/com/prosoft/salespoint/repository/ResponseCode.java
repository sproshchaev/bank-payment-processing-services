package com.prosoft.salespoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCode extends JpaRepository<ResponseCode, Long> {

}
