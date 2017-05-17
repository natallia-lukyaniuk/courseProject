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
import edu.bsuir.univer.entity.Provider;
import edu.bsuir.univer.services.AbstractServices;
import edu.bsuir.univer.services.ProviderServices;

public class ProviderServicesImpl extends AbstractServices implements ProviderServices{
	static final String SQL_PROVIDER_COLUMN = "idprovider, firm, address, phone, bank_account";
	static final String SQL_SELECT_PROVIDERS = String.format("SELECT %s FROM provider ", SQL_PROVIDER_COLUMN);
	static final String SQL_INSERT = "INSERT INTO provider" + "(firm, address, phone, bank_account)"
			+ " VALUES (?,?,?, ?)";
	static final String SQL_SELECT_BY_ID = String.format("SELECT %s FROM provider WHERE idprovider = ? ", SQL_PROVIDER_COLUMN);
	static final String SQL_DELETE = "DELETE FROM provider WHERE idprovider = ?";
	static final String SQL_UPDATE = "UPDATE provider  SET "
			+ "firm = ?, address = ?, phone = ?, bank_account = ? WHERE idprovider = ?";

	@Override
	public List<Provider> getAll() throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<Provider> dao = new DAO<>();
			List<Provider> result = dao.getEntity(SQL_SELECT_PROVIDERS, new ParserProvider(), null, connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public Provider getById(Integer id) throws DAOException {
		Connection connection = null;
		List<Provider> result = null;
		try {
			connection = getConnecton();
			DAO<Provider> dao = new DAO<>();
			result = dao.getEntity(SQL_SELECT_BY_ID, new ParserProvider(), new PSSetId(id), connection);
		} finally {
			DAO.closeConnection(connection);
		}
		if (result.isEmpty())
			return null;
		if (result.size() != 1)
			throw new DAOException("Find app's more than one");
		return result.get(0);
	}

	public Provider createEntity(Provider entity, Connection connection) throws DAOException {

		DAO<Provider> dao = new DAO<>();
		Provider result = dao.create(SQL_INSERT, new ParserProviderCreate(entity), new PSProviderCreate(entity), connection);
		return result;

	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();

			DAO<Provider> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_DELETE, new PSSetId(id), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public boolean update(Provider entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<Provider> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_UPDATE, new PSProviderUpdate(entity), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public Provider create(Provider entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			return createEntity(entity, connection);
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public List<Provider> createAll(List<Provider> entities) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			List<Provider> result = new ArrayList<>();
			for (Provider provider : entities) {
				result.add(createEntity(provider, connection));
			}
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}
	
	class ParserProvider implements Parser<Provider> {

		@Override
		public Provider parse(ResultSet rs) throws SQLException {
			Provider provider = new Provider();
			provider.setId(rs.getInt(1));
			provider.setFirm(rs.getString(2));
			provider.setAddress(rs.getString(3));
			provider.setPhone(rs.getString(4));
			provider.setBankAccount(rs.getInt(5));
			
			return provider;
		}

	}

	class PSProviderCreate implements ParameterSetter {

		private Provider provider;

		public PSProviderCreate(Provider provider) {
			this.provider = provider;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setString(1, provider.getFirm());
			ps.setString(2, provider.getAddress());
			ps.setString(3, provider.getPhone());
			ps.setInt(4, provider.getBankAccount());
		}
	}

	class PSProviderUpdate implements ParameterSetter {

		private Provider provider;

		public PSProviderUpdate(Provider provider) {
			this.provider = provider;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setString(1, provider.getFirm());
			ps.setString(2, provider.getAddress());
			ps.setString(3, provider.getPhone());
			ps.setInt(4, provider.getBankAccount());
			ps.setInt(5, provider.getId());
		}
	}

	class ParserProviderCreate implements Parser<Provider> {

		Provider provider;

		public ParserProviderCreate(Provider provider) {
			this.provider = provider;
		}

		@Override
		public Provider parse(ResultSet rs) throws SQLException {
			provider.setId(rs.getInt(1));
			return provider;
		}

	}

}
