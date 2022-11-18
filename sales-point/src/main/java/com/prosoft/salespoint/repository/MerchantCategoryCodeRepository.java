package com.prosoft.salespoint.repository;


import com.prosoft.salespoint.model.entity.MerchantCategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCategoryCodeRepository extends JpaRepository<MerchantCategoryCode, Long> {

}
