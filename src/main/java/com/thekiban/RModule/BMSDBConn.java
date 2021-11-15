package com.thekiban.RModule;

import java.sql.*;
import java.util.*;

public class BMSDBConn {

	String url="jdbc:mysql://localhost:3306/bmsdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	String user = "bms";
	String pass = "bms!@#$";

	public Connection conn = null;
    public Statement stmt = null;
	public ResultSet rs = null;

	public BMSDBConn(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(url,user,pass);
			conn.setAutoCommit(true);
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		 } 
	}
}