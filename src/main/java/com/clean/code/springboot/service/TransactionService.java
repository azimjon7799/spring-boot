package com.clean.code.springboot.service;

import com.clean.code.springboot.model.Transaction;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {
    private final String URI = "http://localhost:8081/api/transactions";
    public String getMessage(){
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject(URI + "/test", String.class);
        return message;
    }

    public ResponseEntity<Transaction> save(Transaction transaction){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Transaction> newTransaction = restTemplate.postForEntity(URI, transaction, Transaction.class);
        return newTransaction;
    }

    public List<Transaction> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI+"/test", HttpMethod.GET, entity, List.class).getBody();
    }

    public Transaction saveExch(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI, HttpMethod.POST, entity, Transaction.class).getBody();
    }

    public Transaction update(Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Transaction> entity = new HttpEntity<>(transaction, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI, HttpMethod.PUT, entity, Transaction.class).getBody();
    }

}
