package com.ntels.udp.api.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ntels.udp.api.controller.DataController;

@Component
public class SQLConnectionUtil {
	private static final Logger logger = LoggerFactory.getLogger(SQLConnectionUtil.class);
	
	public Connection getConnection() throws Exception {
		// Connect to database
		String hostName = "udpdbserver01.database.windows.net";
		String dbName = "UDPDB01";
		String user = "udpadmin";
		String password = "P@ssw0rd";
		/*
		 * String hostName = "smyun-sqldatabase.database.windows.net"; String dbName =
		 * "smyun-sqldatabase"; String user = "smyun"; String password = "hackfest12!@";
		 */

		String url = "jdbc:sqlserver://" + hostName + ":1433;database=" + dbName + ";user=" + user + ";password="
				+ password
				+ ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		Connection connection = null;

		logger.info("SQL Database Connection Start");

		// check that the driver is installed
		try {
			logger.info("Class Load ");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			logger.info("Class Load Success ");
		} catch (ClassNotFoundException e) {
			logger.warn("JDBC driver NOT detected in library path." + e.getMessage());
		}

		return DriverManager.getConnection(url);
	}

}
