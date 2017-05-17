package edu.bsuir.univer.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bsuir.univer.dao.DAO;
import edu.bsuir.univer.dao.DAOException;
import edu.bsuir.univer.dao.ParameterSetter;
import edu.bsuir.univer.dao.Parser;
import edu.bsuir.univer.entity.User;
import edu.bsuir.univer.services.AbstractServices;
import edu.bsuir.univer.services.UserServices;

public class UserServicesImpl extends AbstractServices implements UserServices {

	static final String SQL_USER_COLUMN = "user_id, login, password, admin";
	static final String SQL_SELECT_USERS = String.format("SELECT %s FROM user ", SQL_USER_COLUMN);
	static final String SQL_INSERT = "INSERT INTO user" + "(login, password, admin)"
			+ " VALUES (?,?,?)";
	static final String SQL_SELECT_BY_ID = String.format("SELECT %s FROM user WHERE user_id = ? ", SQL_USER_COLUMN);
	static final String SQL_DELETE = "DELETE FROM user WHERE user_id = ?";
	static final String SQL_UPDATE = "UPDATE user  SET "
			+ "login = ?, admin = ? WHERE user_id = ?";

	@Override
	public List<User> getAll() throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<User> dao = new DAO<>();
			List<User> result = dao.getEntity(SQL_SELECT_USERS, new ParserUser(), null, connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public User getById(Integer id) throws DAOException {
		Connection connection = null;
		List<User> result = null;
		try {
			connection = getConnecton();
			DAO<User> dao = new DAO<>();
			result = dao.getEntity(SQL_SELECT_BY_ID, new ParserUser(), new PSSetId(id), connection);
		} finally {
			DAO.closeConnection(connection);
		}
		if (result.isEmpty())
			return null;
		if (result.size() != 1)
			throw new DAOException("Find app's more than one");
		return result.get(0);
	}

	public User createEntity(User entity, Connection connection) throws DAOException {

		DAO<User> dao = new DAO<>();
		User result = dao.create(SQL_INSERT, new ParserUserCreate(entity), new PSUserCreate(entity), connection);
		return result;

	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();

			DAO<User> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_DELETE, new PSSetId(id), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public boolean update(User entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<User> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_UPDATE, new PSUserUpdate(entity), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public User create(User entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			return createEntity(entity, connection);
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public List<User> createAll(List<User> entities) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			List<User> result = new ArrayList<>();
			for (User user : entities) {
				result.add(createEntity(user, connection));
			}
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}
}

class ParserUser implements Parser<User> {

	@Override
	public User parse(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(1));
		user.setLogin(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setAdmin(rs.getBoolean(4));
		
		return user;
	}

}

class PSUserCreate implements ParameterSetter {

	private User user;

	public PSUserCreate(User user) {
		this.user = user;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setString(1, user.getLogin());
		ps.setString(2, user.getPassword());
		ps.setBoolean(3, user.isAdmin());
	}
}

class PSUserUpdate implements ParameterSetter {

	private User user;

	public PSUserUpdate(User user) {
		this.user = user;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setString(1, user.getLogin());
		ps.setBoolean(2, user.isAdmin());
		ps.setInt(3, user.getId());
	}
}

class ParserUserCreate implements Parser<User> {

	User user;

	public ParserUserCreate(User user) {
		this.user = user;
	}

	@Override
	public User parse(ResultSet rs) throws SQLException {
		user.setId(rs.getInt(1));
		return user;
	}

}
