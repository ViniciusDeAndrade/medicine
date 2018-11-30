package br.com.medicine.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Person person;

	@JsonManagedReference(value = "job-historical")
	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MedicalConsultation> medicalConsultations;

	@Enumerated
	private Type type;

	@ElementCollection
	private List<String> specialties;

	public Doctor() {}

	public Doctor(Person person, List<String> specialties, 
			Type type, List<MedicalConsultation> medicalConsultations) {
		this.setPerson(person);
		this.setMedicalConsultations(medicalConsultations);
		this.setSpecialties(specialties);
		this.setType(type);
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public Long getId() {
		return id;
	}

	public List<MedicalConsultation> getMedicalConsultations() {
		return medicalConsultations;
	}

	public void setMedicalConsultations(List<MedicalConsultation> medicalConsultations) {
		this.medicalConsultations = medicalConsultations;

	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<String> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<String> specialties) {
		this.specialties = specialties;
	}

}
