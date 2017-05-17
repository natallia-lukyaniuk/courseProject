package edu.bsuir.univer.services;

import java.util.List;

import edu.bsuir.univer.dao.DAOException;

public interface CRUDServices<T>  {
	public List<T> getAll() throws DAOException;
	public T getById(Integer id) throws DAOException;
	public T create(T entity) throws DAOException;
	public boolean delete(Integer id) throws DAOException;
	public boolean update(T entity) throws DAOException;
	public List<T> createAll(List<T> entities) throws DAOException;
}
