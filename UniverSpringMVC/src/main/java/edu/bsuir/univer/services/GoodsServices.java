package edu.bsuir.univer.services;

import java.util.List;

import edu.bsuir.univer.dao.DAOException;
import edu.bsuir.univer.entity.Goods;
import edu.bsuir.univer.entity.ReportType;

public interface GoodsServices extends CRUDServices<Goods>{
	public ReportType getGoodsTypeReport(String type) throws DAOException;
}
