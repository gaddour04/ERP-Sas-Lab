
package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;


import tn.itbs.erp.entites.Client;
import tn.itbs.erp.exception.ClientNotFoundException;
import tn.itbs.erp.repository.ClientRepository;





@Service

@Transactional
public class ClientService implements IClientService{


	@Autowired ClientRepository clientRepository;
	
	@Override
	public Client save(Client client) {
		
		return clientRepository.save(client);
	}
	
	@Override
	public Client updateClient(long id, Client clientdetails) throws ClientNotFoundException {
		Client client=clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
		client.setId(id);
		client.setEmail(clientdetails.getEmail());
		client.setMatriculeFiscale(clientdetails.getMatriculeFiscale());
		client.setFirstName(clientdetails.getFirstName());
		client.setLastName(clientdetails.getLastName());
		client.setTelephone(clientdetails.getTelephone());
		client.setAdresse(clientdetails.getAdresse());
		client.setFactures(clientdetails.getFactures());
		client.setAvoirs(clientdetails.getAvoirs());
		
		
		return clientRepository.save(client);
	}
	/*
	 * @Override public User deleteUser(long id) throws UserNotFoundException { User
	 * user=userrepository.findById(id).orElseThrow(()-> new
	 * UserNotFoundException(id));
	 * 
	 * return userrepository.delete(user); }
	 */
	@Override
	public void deleteClient(long id) throws ClientNotFoundException {
		Client client=clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
		
		clientRepository.delete(client);
	}
	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}
	@Override
	public Optional<Client> findById(long id) throws ClientNotFoundException {
		// TODO Auto-generated method stub
		return clientRepository.findById(id);
	}
	@Override
	public Page<Client> findAllPagination(int p, int s) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(PageRequest.of(p, s));
	}



}
