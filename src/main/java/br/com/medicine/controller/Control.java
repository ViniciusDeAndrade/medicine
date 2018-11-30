package br.com.medicine.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.medicine.model.Address;
import br.com.medicine.model.Client;
import br.com.medicine.model.Doctor;
import br.com.medicine.model.MedicalConsultation;
import br.com.medicine.model.Person;
import br.com.medicine.repository.AddressRepository;
import br.com.medicine.repository.ClientRepository;
import br.com.medicine.repository.DoctorRepository;
import br.com.medicine.repository.MedicalConsultationRepository;
import br.com.medicine.repository.PersonRepository;

@RestController
public class Control {
	
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private MedicalConsultationRepository consultationRepo;
	
	
	@PostMapping("/new/client")
	@CrossOrigin
	public ResponseEntity<Object> createClient(@RequestBody Client client) {
		Client c = this.clientRepo.save(client);		
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(c.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/new/doctor")
	@CrossOrigin
	public ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor) {
		Doctor d = this.doctorRepo.save(doctor);		
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(d.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	@GetMapping("/doctors")
	public List<Doctor> getDoctors(){
		return this.doctorRepo.findAll();
	}
	@GetMapping("/clients")
	public List<MedicalConsultation> getClients(){		
		List<Client> clients = this.clientRepo.findAll();
		return clients.get(0).getMedicalConsultations();
	}
	
	@CrossOrigin("http://localhost:8100")
	@GetMapping("consultations")
	public List<MedicalConsultation> getConsultations() {
		List<MedicalConsultation> consultations = this.consultationRepo.findAll();
		
		return consultations;
	}
	@GetMapping("/persons")
	public List<Person> getPeople() {
		return this.personRepo.findAll();
	}
	
	@GetMapping("/persons/{id}")
	public Person getPerson(@PathVariable(value = "id") Long id) {
		return this.personRepo.findById(id).orElse(null);
	}
	
	@PutMapping("/persons/{id}")
	public void updatePerson( @PathVariable( value = "id") Long id,
			@RequestBody Person newPerson) {
		
		Person oldPerson = this.personRepo.findById(id).orElse(null);
		if(oldPerson == null) {
			return;
		}
		oldPerson.setAddress(newPerson.getAddress());
		oldPerson.setEmail(newPerson.getEmail());
		oldPerson.setName(newPerson.getName());
		oldPerson.setPassword(newPerson.getPassword());
		this.personRepo.save(oldPerson);	
	}
	
	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable( value = "id") Long id) {
		this.personRepo.delete(this.personRepo.findById(id).get());
	}
	
	@PostMapping("new/consultation")
	public ResponseEntity<Object> createConsultation(@RequestBody MedicalConsultation consultation) {
		if(this.clientRepo.findById(consultation.getClient().getId()) == null
				|| this.doctorRepo.findById(consultation.getDoctor().getId()) == null) {
			return null;
		}
		MedicalConsultation mc = this.consultationRepo.save(consultation);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(mc.getId())
				.toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	
	@GetMapping("consultations/{id}")
	public ResponseEntity<MedicalConsultation> getConsultation(@PathVariable(name="id") Long id) {
		MedicalConsultation mc = this.consultationRepo.findById(id).orElse(null);
		//return ResponseEntity.ok(mc.getClient());
		return ResponseEntity.ok(mc);
	}
	
}
