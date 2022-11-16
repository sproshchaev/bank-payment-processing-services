package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.Terminal;

import java.util.Optional;

public interface TerminalService {
    Optional<Terminal> getByTerminalId(String terminalId);
}
