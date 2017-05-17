package edu.bsuir.univer.services;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import edu.bsuir.univer.dao.DAO;
import edu.bsuir.univer.dao.DAOException;
import edu.bsuir.univer.dbcon.DBFilePaths;

public abstract class AbstractServices {

	static String CONFIG_FILE_PASS = DBFilePaths.getPathRead();

	private static String url;
	private static String username;
	private static String password;

	public static Connection getConnecton() throws DAOException {
		getProperties();
		return DAO.getConnection(url, username, password);
	}

	private static void getProperties() throws DAOException {
		Properties prop = new Properties();
		try (InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_PASS)) {
			prop.load(fis);
			url = prop.getProperty("db.url");
			username = prop.getProperty("db.username");
			password = prop.getProperty("db.password");
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
