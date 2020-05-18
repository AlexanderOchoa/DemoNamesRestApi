package com.example.service.impl;

import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private List<Client> clientList = new ArrayList<>();

    @Override
    public Client get(String code) {
        for (Client client : clientList) {
            if (client.getCode().equals(code)) {
                return client;
            }
        }

        return new Client();
    }

    @Override
    public List<Client> list() {
        return clientList;
    }

    @Override
    public String register(Client client) {
        clientList.add(client);
        return "Register success";
    }

    @Override
    public String update(Client client) {
        Client clientFound = get(client.getCode());
        if (clientFound != null) {
            clientFound.setName(client.getName());
            return "Update success";
        } else {
            return "Client not found";
        }
    }

    @Override
    public String delete(String code) {
        Client clientFound = get(code);
        if (clientFound.getCode() != null) {
            clientList.remove(clientFound);
            return "Elimination success";
        } else {
            return "Client not found";
        }
    }

    @Override
    public Client getByName(String name) {
        for (Client client : clientList) {
            if (client.getName().equals(name)) {
                return client;
            }
        }

        return new Client();
    }

    @Override
    public Client getLast() {
        Client client = clientList.get(clientList.size()-1);
        return client;
    }

}
