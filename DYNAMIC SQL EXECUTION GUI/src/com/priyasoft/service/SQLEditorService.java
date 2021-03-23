package com.priyasoft.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLEditorService {
 Connection con;
 Statement st;
 ResultSet rs;
 boolean status;
 public SQLEditorService()
 {
	 try{
		Class.forName("oracle.jdbc.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","admin");
		st=con.createStatement();
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
 }
 public boolean execute(String query)
 {
	 try{
		 status=st.execute(query);
	 }catch(Exception e)
 
	 {
      e.printStackTrace();	 
	 }
	 return status;
}
 public ArrayList<String> getData()
 {
	 ArrayList<String> data=new ArrayList<String>();
	 
	 try{
		 rs=st.getResultSet();
		 ResultSetMetaData md=rs.getMetaData();
		 int colCount=md.getColumnCount();
		 String headers="";
		 for(int i=1;i<=colCount;i++)
		 {
			 headers=headers+md.getColumnName(i)+"  ";
			 
		 }
		 data.add(headers);
		 while(rs.next())
		 {
			 String row="";
			 for(int i=1;i<=colCount;i++)
			 {
				 row=row+rs.getString(i)+"  ";
			 }
			 data.add(row);
		 }
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 return data;
 }
 public int getRowCount()
 {int rowCount=0;
	 try{
		 rowCount=st.getUpdateCount();
		 
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 return rowCount;
 }
 
}
