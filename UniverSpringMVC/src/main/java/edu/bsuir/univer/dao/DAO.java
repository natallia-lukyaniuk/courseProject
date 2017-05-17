package edu.bsuir.univer.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAO<Entity> {

	protected final Logger log = LogManager.getLogger(Class.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			
		}
	}

	public List<Entity> getEntity(String query, Parser<Entity> parser, ParameterSetter setter, Connection connection)
			throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(query);
			if (setter != null)
				setter.setParams(ps);
			rs = ps.executeQuery();
			List<Entity> list = new ArrayList<>();
			while (rs.next()) {
				Entity en = parser.parse(rs);
				list.add(en);
			}
//			log.info("test");
			return list;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO log
					throw new DAOException(e);
				}
			}

		}
	}

	public Entity create(String query, Parser<Entity> parser, ParameterSetter setter, Connection connection)
			throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			setter.setParams(ps);
			if (ps.executeUpdate() != 1)
				throw new DAOException("Can not create entity!");
			if (parser != null) {
				rs = ps.getGeneratedKeys();
				rs.next();
				return parser.parse(rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO log
				throw new DAOException(e);
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO log
					throw new DAOException(e);
				}
			}
		}
	}

	public boolean deleteOrUpdate(String query, ParameterSetter setter, Connection connection) throws DAOException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			setter.setParams(ps);
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DAOException(e);
				// TODO log
			}
		}
	}

	public static Connection getConnection(String url, String username, String password) throws DAOException {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new DAOException(e);
			// TODO log
		}
	}

	//
	public static void closeConnection(Connection connection) throws DAOException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DAOException(e);
			// TODO log
		}
	}

}
