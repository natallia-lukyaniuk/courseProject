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
import edu.bsuir.univer.entity.GoodsSupply;
import edu.bsuir.univer.services.fact.ServicesFactory;
import edu.bsuir.univer.services.fact.ServicesFactoryImpl;

@RestController
public class GoodsSupplyController {
private ServicesFactory servicesFact = new ServicesFactoryImpl();

	
	@GetMapping("/goodsSupplys")
	public List<GoodsSupply> getGoodsSupply() throws ServerException {
		
		try {
			List<GoodsSupply> goodsSupply = servicesFact.getGoodsSupplyServices().getAll();
			return goodsSupply;
		} catch (DAOException e) {
			throw new ServerException(e.getMessage());
		}
	}

	@GetMapping("/goodsSupplys/{id}")
	public ResponseEntity<GoodsSupply> getGoods(@PathVariable("id") Integer id) throws DAOException {

		GoodsSupply goodsSupply = servicesFact.getGoodsSupplyServices().getById(id);
		if (goodsSupply != null) {
			return new ResponseEntity<GoodsSupply>(goodsSupply, HttpStatus.OK);	
		}
		return new ResponseEntity<GoodsSupply>(HttpStatus.NOT_FOUND);
	}	

	@PostMapping(value = "/goodsSupplys")
	public ResponseEntity<GoodsSupply> createGoodsSupply(@RequestBody GoodsSupply goodsSupply) throws DAOException {		
		servicesFact.getGoodsSupplyServices().create(goodsSupply);
		
		return new ResponseEntity(goodsSupply, HttpStatus.OK);
	}

	@DeleteMapping("/goodsSupplys/{id}")
	public ResponseEntity deleteGoodsSupply(@PathVariable Integer id) throws DAOException {

		if (!servicesFact.getGoodsSupplyServices().delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/goodsSupplys/{id}")
	public ResponseEntity updateGoodsSupply(@PathVariable Integer id, @RequestBody GoodsSupply goodsSupply) throws DAOException {
				
		if (!servicesFact.getGoodsSupplyServices().update(goodsSupply)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);
	}
}
