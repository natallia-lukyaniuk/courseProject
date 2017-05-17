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
import edu.bsuir.univer.entity.GoodsType;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class GoodsTypeController {
private ServicesFactory servicesFact = new ServicesFactoryImpl();

	
	@GetMapping("/goodsTypes")
	public List<GoodsType> getGoodsType() throws ServerException {
		
		try {
			List<GoodsType> goodsTypes = servicesFact.getGoodsTypeServices().getAll();
			return goodsTypes;
		} catch (DAOException e) {
			throw new ServerException(e.getMessage());
		}
	}

	@GetMapping("/goodsTypes/{id}")
	public ResponseEntity<GoodsType> getGoodsType(@PathVariable("id") Integer id) throws DAOException {

		GoodsType goodsType = servicesFact.getGoodsTypeServices().getById(id);
		if (goodsType != null) {
			return new ResponseEntity<GoodsType>(goodsType, HttpStatus.OK);	
		}
		return new ResponseEntity<GoodsType>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping(value = "/goodsTypes")
	public ResponseEntity<GoodsType> createGoodsType(@RequestBody GoodsType goodsType) throws DAOException {		
		servicesFact.getGoodsTypeServices().create(goodsType);
		
		return new ResponseEntity(goodsType, HttpStatus.OK);
	}

	@DeleteMapping("/goodsTypes/{id}")
	public ResponseEntity deleteGoodsType(@PathVariable Integer id) throws DAOException {

		if (!servicesFact.getGoodsTypeServices().delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/goodsTypes/{id}")
	public ResponseEntity updateGoodsType(@PathVariable Integer id, @RequestBody GoodsType goodsType) throws DAOException {
				
		if (!servicesFact.getGoodsTypeServices().update(goodsType)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);
	}
}
