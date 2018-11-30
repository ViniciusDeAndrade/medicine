package br.com.medicine.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.medicine.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
