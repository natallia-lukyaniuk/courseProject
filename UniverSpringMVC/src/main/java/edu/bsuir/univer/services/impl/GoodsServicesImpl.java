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
import edu.bsuir.univer.entity.Goods;
import edu.bsuir.univer.entity.ReportType;
import edu.bsuir.univer.services.AbstractServices;
import edu.bsuir.univer.services.GoodsServices;

public class GoodsServicesImpl extends AbstractServices implements GoodsServices {
	static final String SQL_GOODS_COLUMN = "idgoods, name, measure, provider.firm, type";
	static final String SQL_SELECT_GOODS = String.format(
			"SELECT %s FROM (goods JOIN provider ON goods.idprovider = provider.idprovider) JOIN goods_type on goods.type_id = goods_type.type_id ",
			SQL_GOODS_COLUMN);
	static final String SQL_INSERT = "INSERT INTO goods (name, measure, idprovider, type_id) SELECT ?, ?, idprovider, type_id FROM provider, goods_type WHERE firm = ? AND goods_type.type = ? ";
	static final String SQL_SELECT_BY_ID = String.format("%s WHERE idgoods = ? ", SQL_SELECT_GOODS);
	static final String SQL_DELETE = "DELETE FROM goods WHERE idgoods = ?";
	static final String SQL_UPDATE = "UPDATE goods SET name = ?, measure = ?, goods.idprovider = provider.idprovider, goods.type_id = goods_type.type_id FROM provider, goods_type WHERE firm = ? AND goods_type.type = ? WHERE idgoods = ? ;";
	static final String SQL_SELECT_GOODS_TYPE_REPORT = String.format("%s WHERE type = ? ", SQL_SELECT_GOODS,
			SQL_GOODS_COLUMN);

	@Override
	public List<Goods> getAll() throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<Goods> dao = new DAO<>();
			List<Goods> result = dao.getEntity(SQL_SELECT_GOODS, new ParserGoods(), null, connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public Goods getById(Integer id) throws DAOException {
		Connection connection = null;
		List<Goods> result = null;
		try {
			connection = getConnecton();
			DAO<Goods> dao = new DAO<>();
			result = dao.getEntity(SQL_SELECT_BY_ID, new ParserGoods(), new PSSetId(id), connection);
		} finally {
			DAO.closeConnection(connection);
		}
		if (result.isEmpty())
			return null;
		if (result.size() != 1)
			throw new DAOException("Find app's more than one");
		return result.get(0);
	}

	public Goods createEntity(Goods entity, Connection connection) throws DAOException {

		DAO<Goods> dao = new DAO<>();
		Goods result = dao.create(SQL_INSERT, new ParserGoodsCreate(entity), new PSGoodsCreate(entity), connection);
		return result;

	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();

			DAO<Goods> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_DELETE, new PSSetId(id), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public boolean update(Goods entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			DAO<Goods> dao = new DAO<>();
			boolean result = dao.deleteOrUpdate(SQL_UPDATE, new PSGoodsUpdate(entity), connection);
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public Goods create(Goods entity) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			return createEntity(entity, connection);
		} finally {
			DAO.closeConnection(connection);
		}
	}

	@Override
	public List<Goods> createAll(List<Goods> entities) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnecton();
			List<Goods> result = new ArrayList<>();
			for (Goods goods : entities) {
				result.add(createEntity(goods, connection));
			}
			return result;
		} finally {
			DAO.closeConnection(connection);
		}
	}
	
	@Override
	public ReportType getGoodsTypeReport(String type) throws DAOException{
		Connection connection = null;
		List<Goods> goodList = null;
		ReportType report = new ReportType();
		try {
			connection = getConnecton();
			DAO<Goods> dao = new DAO<>();
			goodList = dao.getEntity(SQL_SELECT_GOODS_TYPE_REPORT, new ParserGoods(), new PSSetTypeReports(type), connection);
			report.setGoods(goodList);
		} finally {
			DAO.closeConnection(connection);
		}
		return report;
	}
}

class ParserGoods implements Parser<Goods> {

	@Override
	public Goods parse(ResultSet rs) throws SQLException {
		Goods goods = new Goods();

		goods.setId(rs.getInt(1));
		goods.setName(rs.getString(2));
		goods.setMeasure(rs.getString(3));
		goods.setFirm(rs.getString(4));
		goods.setType(rs.getString(5));

		return goods;
	}

}

class PSGoodsCreate implements ParameterSetter {

	private Goods goods;

	public PSGoodsCreate(Goods goods) {
		this.goods = goods;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setString(1, goods.getName());
		ps.setString(2, goods.getMeasure());
		ps.setString(3, goods.getFirm());
		ps.setString(4, goods.getType());
	}
}

class PSGoodsUpdate implements ParameterSetter {

	private Goods goods;

	public PSGoodsUpdate(Goods goods) {
		this.goods = goods;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setString(1, goods.getName());
		ps.setString(2, goods.getMeasure());
		ps.setString(4, goods.getFirm());
		ps.setString(5, goods.getType());
		ps.setInt(3, goods.getId());
	}
}

class ParserGoodsCreate implements Parser<Goods> {

	Goods goods;

	public ParserGoodsCreate(Goods goods) {
		this.goods = goods;
	}

	@Override
	public Goods parse(ResultSet rs) throws SQLException {
		goods.setId(rs.getInt(1));
		return goods;
	}

}

class PSSetId implements ParameterSetter {

	private int id;

	public PSSetId(int id) {
		this.id = id;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setInt(1, id);
	}
}

class PSSetTypeReports implements ParameterSetter {

	private String type;

	public PSSetTypeReports(String type) {
		this.type = type;
	}

	@Override
	public void setParams(PreparedStatement ps) throws SQLException {
		ps.setString(1, type);
	}
}

class ParserReportType implements Parser<ReportType> {

	@Override
	public ReportType parse(ResultSet rs) throws SQLException {
		ReportType reportType = new ReportType();

		
		
		return reportType;
	}

}
