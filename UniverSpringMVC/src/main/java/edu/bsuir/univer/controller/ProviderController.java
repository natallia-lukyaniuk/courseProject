package edu.bsuir.univer.controller;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bsuir.univer.dao.DAOException;
import edu.bsuir.univer.entity.Provider;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class ProviderController {
private ServicesFactory servicesFact = new ServicesFactoryImpl();

	
	@GetMapping("/providers")
	public List<Provider> getProviders() throws ServerException {
		
		try {
			List<Provider> providers = servicesFact.getProviderServices().getAll();
			return providers;
		} catch (DAOException e) {
			throw new ServerException(e.getMessage());
		}
	}

	@GetMapping("/providers/{id}")
	public ResponseEntity<Provider> getProvider(@PathVariable("id") Integer id) throws DAOException {

		Provider providers = servicesFact.getProviderServices().getById(id);
		if (providers != null) {
			return new ResponseEntity<Provider>(providers, HttpStatus.OK);	
		}
		return new ResponseEntity<Provider>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping(value = "/providers")
	public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) throws DAOException {		
		servicesFact.getProviderServices().create(provider);
		
		return new ResponseEntity(provider, HttpStatus.OK);
	}

	@DeleteMapping("/providers/{id}")
	public ResponseEntity deleteProvider(@PathVariable Integer id) throws DAOException {

		if (!servicesFact.getProviderServices().delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/providers/{id}")
	public ResponseEntity updateProvider(@PathVariable Integer id, @RequestBody Provider provider) throws DAOException {
				
		if (!servicesFact.getProviderServices().update(provider)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);
	}
}
