package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TransliterationServiceImpl implements TransliterationService {
    @Override
    public String getTransliterationName(Client client) {
        return client.getFirstName().toUpperCase(Locale.ROOT) + " "
                + client.getMiddleName().substring(0, 1).toUpperCase(Locale.ROOT) + ". "
                + client.getLastName().toUpperCase(Locale.ROOT);
    }
}
