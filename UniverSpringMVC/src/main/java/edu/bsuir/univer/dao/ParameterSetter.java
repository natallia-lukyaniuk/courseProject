package edu.bsuir.univer.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterSetter {

	void setParams(PreparedStatement ps) throws SQLException;
}
