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
import edu.bsuir.univer.entity.GoodsType;
import edu.bsuir.univer.services.AbstractServices;
import edu.bsuir.univer.services.GoodsTypeServices;
import edu.bsuir.univer.services.impl.GoodsTypeServicesImpl.PSGoodsTypeCreate;
import edu.bsuir.univer.services.impl.GoodsTypeServicesImpl.PSGoodsTypeUpdate;
import edu.bsuir.univer.services.impl.GoodsTypeServicesImpl.ParserGoodsType;
import edu.bsuir.univer.services.impl.GoodsTypeServicesImpl.ParserGoodsTypeCreate;

public class GoodsTypeServicesImpl extends AbstractServices implements GoodsTypeServices{
	static final String SQL_GOODSTYPE_COLUMN = "type_id, type";
	static final String SQL_SELECT_GOODSTYPE = String.format("SELECT %s FROM goods_type ", SQL_GOODSTYPE_COLUMN);
	static final String SQL_INSERT = "INSERT INTO goods_type" + "(type)"
			+ " VALUES (?)";
	static final String SQL_SELECT_BY_ID = String.format("SELECT %s FROM goods_type WHERE type_id = ? ", SQL_GOODSTYPE_COLUMN);
	static final String SQL_DELETE = "DELETE FROM goods_type WHERE type_id = ?";
	static final String SQL_UPDATE = "UPDATE goods_type  SET "
			+ "type = ? WHERE type_id = ?";

	@Override
	public List<GoodsType> getAll() throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<GoodsType> dao = new DAO<>();
			List<GoodsType> result = dao.getEntity(SQL_SELECT_GOODSTYPE, new ParserGoodsType(), null, connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public GoodsType getById(Integer id) throws DAOException {
		Connection connection = null;
		List<GoodsType> result = null;
		try {
			connection = getConnecton();
			DAO<GoodsType> dao = new DAO<>();
			result = dao.getEntity(SQL_SELECT_BY_ID, new ParserGoodsType(), new PSSetId(id), connection);
		} finally {
			DAO.closeConnection(connection);
		}
		if (result.isEmpty())
			return null;
		if (result.size() != 1)
			throw new DAOException("Find app's more than one");
		return result.get(0);
	}

	public GoodsType createEntity(GoodsType entity, Connection connection) throws DAOException {

		DAO<GoodsType> dao = new DAO<>();
		GoodsType result = dao.create(SQL_INSERT, new ParserGoodsTypeCreate(entity), new PSGoodsTypeCreate(entity), connection);
		return result;

	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();

			DAO<GoodsType> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_DELETE, new PSSetId(id), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public boolean update(GoodsType entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<GoodsType> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_UPDATE, new PSGoodsTypeUpdate(entity), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public GoodsType create(GoodsType entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			return createEntity(entity, connection);
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public List<GoodsType> createAll(List<GoodsType> entities) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			List<GoodsType> result = new ArrayList<>();
			for (GoodsType provider : entities) {
				result.add(createEntity(provider, connection));
			}
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
}
	class ParserGoodsType implements Parser<GoodsType> {

		@Override
		public GoodsType parse(ResultSet rs) throws SQLException {
			GoodsType goodsType = new GoodsType();
			goodsType.setId(rs.getInt(1));
			goodsType.setType(rs.getString(2));
			
			return goodsType;
		}

	}

	class PSGoodsTypeCreate implements ParameterSetter {

		private GoodsType goodsType;

		public PSGoodsTypeCreate(GoodsType goodsType) {
			this.goodsType = goodsType;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setString(1, goodsType.getType());
		}
	}

	class PSGoodsTypeUpdate implements ParameterSetter {

		private GoodsType goodsType;

		public PSGoodsTypeUpdate(GoodsType goodsType) {
			this.goodsType = goodsType;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setString(1, goodsType.getType());
			ps.setInt(2, goodsType.getId());
		}
	}

	class ParserGoodsTypeCreate implements Parser<GoodsType> {

		GoodsType goodsType;

		public ParserGoodsTypeCreate(GoodsType goodsType) {
			this.goodsType = goodsType;
		}

		@Override
		public GoodsType parse(ResultSet rs) throws SQLException {
			goodsType.setId(rs.getInt(1));
			return goodsType;
		}

	}

}
