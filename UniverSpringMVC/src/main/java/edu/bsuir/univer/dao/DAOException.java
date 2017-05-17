package edu.bsuir.univer.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 880755948282781819L;

	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String msg) {
		super(msg);
	}
}
