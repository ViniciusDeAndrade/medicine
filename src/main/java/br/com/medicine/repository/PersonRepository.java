package br.com.medicine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.medicine.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

	@Override
	public List<Person>findAll();
}
