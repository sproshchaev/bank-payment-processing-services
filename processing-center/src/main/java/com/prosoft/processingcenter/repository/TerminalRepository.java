package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    Optional<Terminal> getByTerminalId(String terminalId);
}
