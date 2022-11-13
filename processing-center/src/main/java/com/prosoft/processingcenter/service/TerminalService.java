package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.Terminal;

import java.util.Optional;

public interface TerminalService {
    Optional<Terminal> getByTerminalId(String terminalId);
}
