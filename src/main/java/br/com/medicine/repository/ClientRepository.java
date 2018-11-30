package br.com.medicine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.medicine.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	
	@Override
	public List<Client> findAll();

}
