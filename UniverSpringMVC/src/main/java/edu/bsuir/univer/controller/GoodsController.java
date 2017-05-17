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
import edu.bsuir.univer.entity.Goods;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class GoodsController {
private ServicesFactory servicesFact = new ServicesFactoryImpl();

	
	@GetMapping("/goods")
	public List<Goods> getGoods() throws ServerException {
		
		try {
			List<Goods> goods = servicesFact.getGoodsServices().getAll();
			return goods;
		} catch (DAOException e) {
			throw new ServerException(e.getMessage());
		}
	}

	@GetMapping("/goods/{id}")
	public ResponseEntity<Goods> getGoods(@PathVariable("id") Integer id) throws DAOException {

		Goods goods = servicesFact.getGoodsServices().getById(id);
		if (goods != null) {
			return new ResponseEntity<Goods>(goods, HttpStatus.OK);	
		}
		return new ResponseEntity<Goods>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping(value = "/goods")
	public ResponseEntity<Goods> createGoods(@RequestBody Goods goods) throws DAOException {		
		servicesFact.getGoodsServices().create(goods);
		
		return new ResponseEntity(goods, HttpStatus.OK);
	}

	@DeleteMapping("/goods/{id}")
	public ResponseEntity deleteGoods(@PathVariable Integer id) throws DAOException {

		if (!servicesFact.getGoodsServices().delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/goods/{id}")
	public ResponseEntity updateGoods(@PathVariable Integer id, @RequestBody Goods goods) throws DAOException {
				
		if (!servicesFact.getGoodsServices().update(goods)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);
	}
}
