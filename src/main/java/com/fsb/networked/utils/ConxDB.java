package com.fsb.networked.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConxDB {

	private static Connection connexion;

	private final String DB_URL = "jdbc:mysql://196.229.18.84:3306/networked_db";
	private final String USER = "any_user";
	private final String PASS= "password!1";
	//root root1234
	private ConxDB() throws SQLException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		connexion  = DriverManager.getConnection(DB_URL,USER,PASS);

	}
	public static Connection getInstance() {
		if(connexion == null)
		{
			try
			{
				new ConxDB();
				System.out.println("connected to networked_2 db");
			}
			catch(Exception e)
			{
				System.out.println("--" + e.getMessage());
			}
		}
	return connexion;
	}

}
