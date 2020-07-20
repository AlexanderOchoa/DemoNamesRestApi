package com.example.controller;

import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v2/clients")
public class ClientControllerv2 {

    private ClientService clientService;

    @Autowired
    public ClientControllerv2(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> list() {
        List<Client> list = clientService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/{name}",method = RequestMethod.GET)
    public ResponseEntity<Client> get(@PathVariable(value = "code") String code, @PathVariable(value = "name") String name) {
        Client client = clientService.get(code);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Client client) {
        String result = clientService.register(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Client client) {
        String result = clientService.update(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value = "code") String code) {
        String result = clientService.delete(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-by-name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Client> getByRuc(@PathVariable(value = "name") String name) {
        Client client = clientService.getByName(name);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-last", method = RequestMethod.GET)
    public ResponseEntity<Client> getLast() {
        Client client = clientService.getLast();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
