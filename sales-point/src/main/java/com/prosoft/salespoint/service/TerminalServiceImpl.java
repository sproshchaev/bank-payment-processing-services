package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.Terminal;
import com.prosoft.salespoint.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;

    @Autowired
    public TerminalServiceImpl(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Terminal> getByTerminalId(String terminalId) {
        return terminalRepository.getByTerminalId(terminalId);
    }
}
