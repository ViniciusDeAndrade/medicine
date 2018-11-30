package br.com.medicine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.medicine.model.MedicalConsultation;

public interface MedicalConsultationRepository extends CrudRepository<MedicalConsultation, Long>{

	@Override
	public List<MedicalConsultation> findAll();
}
