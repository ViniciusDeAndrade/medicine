package br.com.medicine.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medical_consultation")
public class MedicalConsultation {

	@Id
	@GeneratedValue
	private Long id;

	//@JsonIgnore
	@JsonBackReference(value = "job-historical")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="doctor_fk")
	private Doctor doctor;
	
	//@JsonIgnore
	@JsonBackReference( value = "client-consultations-historical")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="client_fk")
	private Client client;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date date;

	private BigDecimal price;

	public MedicalConsultation() {}

	public MedicalConsultation(Doctor doctor, Client client, Date date, BigDecimal price) {
		this.setClient(client);
		this.setDate(date);
		this.setDoctor(doctor);
		this.setPrice(price);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
