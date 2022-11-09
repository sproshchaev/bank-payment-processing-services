package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;

public interface TransliterationService {
    String getTransliterationName(Client client);
}
