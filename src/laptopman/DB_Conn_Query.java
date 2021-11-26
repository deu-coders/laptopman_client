package laptopman;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.jdbc.OracleTypes;
import oracle.sql.DATE;


public class DB_Conn_Query {
	private String Adress ;
	private String Port ;
	private String ID ;
	private String PW ;
	Connection con = null;
	
	public void getAdress(String Adress) {
		this.Adress=Adress;
	}
	
	public void getPort(String Port) {
		this.Port=Port;
	}
	
	public void getID(String ID) {
		this.ID=ID;
	}
	
	public void getPW(String PW) {
		this.PW=PW;
	}
	
//	PreparedStatement pstmt = null;
	
   public void DB_Conn() {
	     String url = String.format("jdbc:oracle:thin:@%s:%s:XE", Adress,Port);
	     String id = ID;      String password = PW;
	     try {   Class.forName("oracle.jdbc.driver.OracleDriver");
	        System.out.println("드라이버 적재 성공");
	        con = DriverManager.getConnection(url, id, password);
	        System.out.println("DB 연결 성공");
	     } 
	     catch (ClassNotFoundException e) {         System.out.println("No Driver.");    }
	     catch (SQLException e) {         System.out.println("Connection Fail");      }
	     
   }
   public DefaultTableModel sqlrun(String query,String[] column, int[] type)
   {
	   	 DefaultTableModel DtmStorage;
		 DtmStorage = new DefaultTableModel(column, 0){
			public boolean isCellEditable(int row, int column){ // 테이블을 더블클릭할 때 수정여부 설정
				return false;    // 셀 수정 가능(return true), 불가능 설정(return false)
			}
		 };
		 DtmStorage.setColumnIdentifiers(column);
	   
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String row[] = new String[type.length];
			   while (rs.next()) {
				   for(int i=0;i<type.length;i++) {
					   switch (type[i]) {
					   		case 1: 
					   			if(rs.getString(i+1) != null)
					   				row[i] = rs.getString(i+1).trim();
					   			else
					   				row[i] = rs.getString(i+1);
					   			break;
					   		case 2: 
					   			row[i] = Integer.toString(rs.getInt(i+1));
			   					break;
					   		case 3: 
				   				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				   				row[i] = dateFormat.format(rs.getDate(i+1));
				   				break;
					   }
				   }
				   DtmStorage.addRow(row);
			   }
		    stmt.close();    rs.close();     //con.close();
		   }catch (SQLException e) { e.printStackTrace(); }
		   return DtmStorage;
   }
   
   public void addComboBox(String query, JComboBox comboBox) {
		try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   
			   while (rs.next()) {
				   comboBox.addItem(rs.getString(1));
			   }
		    stmt.close();    rs.close();     //con.close();
		   }catch (SQLException e) { e.printStackTrace(); }
	}
   
   public DefaultTableModel sql_callable(String query, String[] value , String[] column) {
	   DefaultTableModel DtmStorage;
		 DtmStorage = new DefaultTableModel(column, 0){
			public boolean isCellEditable(int row, int column){ // 테이블을 더블클릭할 때 수정여부 설정
				return false;    // 셀 수정 가능(return true), 불가능 설정(return false)
			}
		 };
		 DtmStorage.setColumnIdentifiers(column);
	   
	   try {
		   CallableStatement cstmt = con.prepareCall(query);
		   	   
		   cstmt.setString(1, value[0]);
		   cstmt.setString(2, value[1]);
		   cstmt.setInt(3, Integer.parseInt(value[2]));
		   cstmt.registerOutParameter(4, OracleTypes.CURSOR);
		   cstmt.executeQuery();
		   
		   ResultSet rs =(ResultSet)cstmt.getObject(4);
		   
		   while(rs.next()) {
			   String row[] = new String[column.length];
			   for(int i=0;i<column.length;i++) {
				   if(rs.getString(i+1) != null)
		   				row[i] = rs.getString(i+1).trim();
		   			else
		   				row[i] = rs.getString(i+1);
			   }
			   DtmStorage.addRow(row);
//			   System.out.println(rs.getString(1)+",\t"+rs.getString(2)+",\t"+rs.getString(3));
		   }
		   cstmt.close();	
		   rs.close();	
	   }catch(SQLException e) { e.printStackTrace(); }
	   return DtmStorage;
   }
}
