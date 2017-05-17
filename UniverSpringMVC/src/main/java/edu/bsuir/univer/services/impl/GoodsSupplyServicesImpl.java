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
import edu.bsuir.univer.entity.GoodsSupply;
import edu.bsuir.univer.services.AbstractServices;
import edu.bsuir.univer.services.GoodsSupplyServices;

public class GoodsSupplyServicesImpl extends AbstractServices implements GoodsSupplyServices{
	static final String SQL_GOODSSUPPLY_COLUMN = "idgoods_supply, date, name, amount, price";
	static final String SQL_SELECT_GOODSSUPPLY = String.format("SELECT %s FROM goods_supply JOIN goods ON goods_supply.idgoods = goods.idgoods ", SQL_GOODSSUPPLY_COLUMN);
	static final String SQL_INSERT = "INSERT INTO goods_supply (date, idgoods, amount, price) SELECT ?, idgoods, ?, ? FROM goods WHERE name = ? ";
	static final String SQL_SELECT_BY_ID = String.format("%s WHERE idgoods_supply = ? ", SQL_SELECT_GOODSSUPPLY);
	static final String SQL_DELETE = "DELETE FROM goods_supply WHERE goods_supply = ?";
	static final String SQL_UPDATE = "UPDATE goods_supply, goods SET date = ?, goods_supply.idgoods = goods.idgoods, amount = ?, price = ? WHERE idgoods_supply = ? AND goods.name = ? ;";

	@Override
	public List<GoodsSupply> getAll() throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<GoodsSupply> dao = new DAO<>();
			List<GoodsSupply> result = dao.getEntity(SQL_SELECT_GOODSSUPPLY, new ParserGoodsSupply(), null, connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public GoodsSupply getById(Integer id) throws DAOException {
		Connection connection = null;
		List<GoodsSupply> result = null;
		try {
			connection = getConnecton();
			DAO<GoodsSupply> dao = new DAO<>();
			result = dao.getEntity(SQL_SELECT_BY_ID, new ParserGoodsSupply(), new PSSetId(id), connection);
		} finally {
			DAO.closeConnection(connection);
		}
		if (result.isEmpty())
			return null;
		if (result.size() != 1)
			throw new DAOException("Find app's more than one");
		return result.get(0);
	}

	public GoodsSupply createEntity(GoodsSupply entity, Connection connection) throws DAOException {

		DAO<GoodsSupply> dao = new DAO<>();
		GoodsSupply result = dao.create(SQL_INSERT, new ParserGoodsSupplyCreate(entity), new PSGoodsSupplyCreate(entity), connection);
		return result;

	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();

			DAO<GoodsSupply> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_DELETE, new PSSetId(id), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public boolean update(GoodsSupply entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<GoodsSupply> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_UPDATE, new PSGoodsSupplyUpdate(entity), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public GoodsSupply create(GoodsSupply entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			return createEntity(entity, connection);
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public List<GoodsSupply> createAll(List<GoodsSupply> entities) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			List<GoodsSupply> result = new ArrayList<>();
			for (GoodsSupply goods : entities) {
				result.add(createEntity(goods, connection));
			}
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}
	
	class ParserGoodsSupply implements Parser<GoodsSupply> {

		@Override
		public GoodsSupply parse(ResultSet rs) throws SQLException {
			GoodsSupply goodsSupply = new GoodsSupply();
			
			goodsSupply.setId(rs.getInt(1));
			goodsSupply.setDate(new java.util.Date(rs.getDate(2).getTime()));
			goodsSupply.setName(rs.getString(3));
			goodsSupply.setAmount(rs.getInt(4));
			goodsSupply.setPrice(rs.getInt(5));
			
			return goodsSupply;
		}

	}

	class PSGoodsSupplyCreate implements ParameterSetter {

		private GoodsSupply goodsSupply;

		public PSGoodsSupplyCreate(GoodsSupply goodsSupply) {
			this.goodsSupply = goodsSupply;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setDate(1, new java.sql.Date(goodsSupply.getDate().getTime()));
			ps.setInt(2, goodsSupply.getAmount());
			ps.setInt(3, goodsSupply.getPrice());
			ps.setString(4, goodsSupply.getName());
		}
	}

	class PSGoodsSupplyUpdate implements ParameterSetter {

		private GoodsSupply goodsSupply;

		public PSGoodsSupplyUpdate(GoodsSupply goodsSupply) {
			this.goodsSupply = goodsSupply;
		}

		@Override
		public void setParams(PreparedStatement ps) throws SQLException {
			ps.setDate(1, new java.sql.Date(goodsSupply.getDate().getTime()));
			ps.setInt(2, goodsSupply.getAmount());
			ps.setInt(3, goodsSupply.getPrice());
			ps.setInt(4, goodsSupply.getId());
			ps.setString(5, goodsSupply.getName());
		}
	}

	class ParserGoodsSupplyCreate implements Parser<GoodsSupply> {

		GoodsSupply goodsSupply;

		public ParserGoodsSupplyCreate(GoodsSupply goodsSupply) {
			this.goodsSupply = goodsSupply;
		}

		@Override
		public GoodsSupply parse(ResultSet rs) throws SQLException {
			goodsSupply.setId(rs.getInt(1));
			return goodsSupply;
		}

	}

}
