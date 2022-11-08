package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> getClientById(long clientId);
    List<Client> getClient(Client client);
    Client createClient(Client client);
}
