package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Client;
import com.prosoft.issuingbank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getClientById(long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getClient(Client client) {
        return clientRepository.getClientByPersonalData(client.getLastName(),
                client.getFirstName(), client.getMiddleName(), client.getBirthDate(), client.getDocument());
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        List<Client> clientList = getClient(client);
        if (getClient(client).isEmpty()) {
            Client clientCreated = clientRepository.save(client);
            return clientCreated;
        } else {
            return clientList.get(0);
        }
    }
}
