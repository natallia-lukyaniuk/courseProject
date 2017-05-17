package edu.bsuir.univer.controller;

import java.rmi.ServerException;
import java.util.ArrayList;
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
import edu.bsuir.univer.entity.User;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class UserController {

	private ServicesFactory servicesFact = new ServicesFactoryImpl();

	
	@GetMapping("/users")
	public List<User> getUsers() throws ServerException {
		
		try {
			List<User> users = servicesFact.getUserServices().getAll();
			return users;
		} catch (DAOException e) {
			throw new ServerException(e.getMessage());
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) throws DAOException {

		User user = servicesFact.getUserServices().getById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);	
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping(value = "/users")
	public ResponseEntity<User> createUser(@RequestBody User user) throws DAOException {		
		servicesFact.getUserServices().create(user);
		
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id) throws DAOException {

		if (!servicesFact.getUserServices().delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody User user) throws DAOException {
				
		if (!servicesFact.getUserServices().update(user)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);
	}

}