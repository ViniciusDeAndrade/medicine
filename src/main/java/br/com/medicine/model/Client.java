package br.com.medicine.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "client")
public class Client{

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Person person;
	
	@JsonManagedReference(value = "client-consultations-historical")
	@OneToMany(mappedBy="doctor" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MedicalConsultation> medicalConsultations;
	
	public Client() {}
	
	public Client(Person person, List<MedicalConsultation> medicalConsultations) {
		this.setPerson(person);
		this.setMedicalConsultation(medicalConsultations);
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
	
	public void setMedicalConsultation(List<MedicalConsultation> medicalConsultations) {
		this.medicalConsultations = medicalConsultations;
	}
	
}
