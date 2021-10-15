package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


import tn.itbs.erp.entites.Client;

import tn.itbs.erp.exception.ClientNotFoundException;

public interface IClientService {
	List<Client> findAll();
	Page<Client> findAllPagination(int p,int s);
	public Client save(Client client); 
	Optional<Client> findById(long id) throws ClientNotFoundException;
	public Client updateClient(long id,Client client) throws ClientNotFoundException;
	public void deleteClient(long id) throws ClientNotFoundException;

}
