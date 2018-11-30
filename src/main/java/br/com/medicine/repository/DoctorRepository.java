package br.com.medicine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.medicine.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long>{

	@Override
	public List<Doctor> findAll();
}
