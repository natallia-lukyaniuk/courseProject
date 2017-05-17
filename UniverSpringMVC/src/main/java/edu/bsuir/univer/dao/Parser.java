package edu.bsuir.univer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Parser<T> {

	T parse(ResultSet rs) throws SQLException;
	
}