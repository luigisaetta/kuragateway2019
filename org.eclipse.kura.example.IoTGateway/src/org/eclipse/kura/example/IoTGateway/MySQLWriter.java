/*
 * Luigi Saetta
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Dicembre 2019
 * 
 */
package org.eclipse.kura.example.IoTGateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.PreparedStatement;

public class MySQLWriter
{
	private static final Logger s_logger = LoggerFactory.getLogger(MySQLWriter.class);

	// MySQL
	private static final String CONN_URL = "jdbc:mysql://localhost:3306/kura?user=root&password=welcome1";

	private Connection conn = null;

	private PreparedStatement prepared = null;

	public MySQLWriter()
	{

	}

	public void activate()
	{
		// create MYSQL connection
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(CONN_URL);

			info("Connection to MySQL OK...");

			prepared = (PreparedStatement) conn
					.prepareStatement("insert into MESSAGES (msg, type, TS) values (?, ?, ?)");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void insert(String msg, int iMsg)
	{
		// insert into MySQL
		try
		{
			long ts = System.currentTimeMillis() / 1000; // ts in sec.

			prepared.setString(1, msg);
			prepared.setInt(2, iMsg);
			prepared.setLong(3, ts);

			prepared.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deactivate()
	{
		// close MYSQL connection
		try
		{
			conn.close();

			conn = null;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void info(String msg)
	{
		s_logger.info(msg);
	}
}
