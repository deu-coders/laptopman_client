package laptopman;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	
	public Boolean manager_Login(String manager_id,String manager_pw) {
		String url = String.format("jdbc:oracle:thin:@%s:%s:XE", this.Adress,this.Port);
		try { 
			   con = DriverManager.getConnection(url, manager_id, manager_pw);
		   		System.out.println("관리자 로그인 성공");
		   		return true;
		   } 
		   catch (SQLException e) { 
			   System.out.println("관리자 Connection Fail"); 
			   return false;
		   }
		finally { 
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
   public void DB_Conn() {
	   try { Class.forName("oracle.jdbc.driver.OracleDriver");
	   System.out.println("드라이버 적재 성공");
	   } catch (ClassNotFoundException e) { System.out.println("No Driver."); }
   }
   
   private void DB_Connect() {
	   String url = String.format("jdbc:oracle:thin:@%s:%s:XE", Adress,Port);
	   String id = ID;      String password = PW;
	   try { 
		   con = DriverManager.getConnection(url, id, password);
	   		System.out.println("DB 연결 성공");
	   } 
	   catch (SQLException e) { 
		   System.out.println("Connection Fail"); 
	   }
   }
   
   public String get_delete_query(String table_name,int row,TableModel m) {
	   ResultSet rs = get_pk(table_name);
       int index=0;
       String[] query = null ;
       try {
       	
       	query = new String[2];
			while(rs.next()) { 
				String colName = rs.getString("COLUMN_NAME"); 
				int keySeq = rs.getInt("KEY_SEQ"); 
				query[index]=colName+"='"+m.getValueAt(row, keySeq-1)+"'";
					
//   			String pkName = rs.getString("PK_NAME");
//				System.out.println(colName); 
//				System.out.println(keySeq); 
//   			System.out.println(pkName); 
				index++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
       String querys = "DELETE FROM "+table_name+" WHERE ";
       if(index>1)
       	querys+=String.join(" AND ", query);
       else
       	querys+=query[0];
       
       return querys;
   }
   
   public void delete_data(String query) throws SQLException {
	   DB_Connect();
	   Statement stmt = null;
	   
	   
	   stmt = con.createStatement();
	   stmt.executeUpdate(query);
	   con.close();
   }
   
   public ResultSet get_pk(String table_name) {
	   DB_Connect();
	   ResultSet rs = null;
	   try {
		rs = con.getMetaData().getPrimaryKeys(null, null, table_name);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return rs;
   }
   
   public String[] get_column(String table_name) {
	   String[] column = null;
	   DB_Connect();
	   String sql = "SELECT * FROM ";
	   Statement stmt;
	   ResultSet rs;
	   ResultSetMetaData rsmd; 
	try {
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql+table_name);
		rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		column = new String[cols];
		for(int i = 1; i<=cols; i++){
			column[i-1]=rsmd.getColumnName(i).toString();
//			column[i-1]=rsmd.getColumnName(i).toString()+"("+rsmd.getColumnTypeName(i).toString()+")";
//            System.out.println(rsmd.getColumnName(i)+"\t\t");
        }
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	   return column;
   }
   
   public int[] get_type(String table_name) {
	   int[] type = null;
	   switch(table_name) {
	   		case "CPU":
	   			type=new int[]{1,1,1,2,1};
	   		break;
	   		case "GPU":
	   			type=new int[]{1,1,2,1};
	   		break;
	   		case "운영체제":
	   			type=new int[]{1};
	   		break;
	   		case "제품별가능한프로그램":
	   			type=new int[]{1,2};
	   		break;
	   		case "제품옵션":
	   			type=new int[]{1,1,1,2};
	   		break;
	   		case "제품정보":
	   			type=new int[]{1,1,1,2};
	   		break;
	   		case "프로그램":
	   			type=new int[]{1,1,1};
	   		break;
	   		case "프로그램사양":
	   			type=new int[]{2,1,1,1,1,2};
	   		break;
	   		case "프로그램지원운영체제":
	   			type=new int[]{1,1};
	   		break;
	   }
	   return type;
   }
   
   public void insert_data(String table_name, String[] column) throws SQLException {
	   DB_Connect();
	   String[] Q_mark=new String[column.length];
	   int[] type=get_type(table_name);
	   
	   for(int i=0;i<Q_mark.length;i++)
		   Q_mark[i]="?";
//	   Q_mark.repeat(column.length);
	   String Q_marks=String.join(",", Q_mark);
	   
	   
		PreparedStatement pstmt = con.prepareStatement(String.format("insert into %s values(%s)", table_name, Q_marks));
		for(int i=0;i<type.length;i++) {
			   switch (type[i]) {
			   		case 1: 
			   			pstmt.setString(i+1,column[i]);
			   			break;
			   		case 2: 
			   			pstmt.setInt(i+1, Integer.parseInt(column[i]));
			   			break;
			   		case 3: 
			   			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
						try {
							pstmt.setDate(i+1, (java.sql.Date) transFormat.parse(column[i]));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
			   }
		 }
		pstmt.executeUpdate();
		con.close();
   }
   
   public DefaultTableModel sqlrun(String query,String[] column, int[] type)
   {
	   	 DB_Connect();
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
		   finally { try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		   return DtmStorage;
   }
   
   public void addComboBox(String query, JComboBox comboBox)  {
	    DB_Connect();
		try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   
			   while (rs.next()) {
				   comboBox.addItem(rs.getString(1));
			   }
		    stmt.close();    rs.close();     //con.close();
		   }catch (SQLException e) { e.printStackTrace(); }
		 finally { try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
	}
   
   public DefaultTableModel sql_callable(String query, String[] value , String[] column) {
	   DB_Connect();
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
	   finally { try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
	   return DtmStorage;
   }
   
   public DefaultTableModel specification_callable(String query, String[] value , String[] column) {
	   DB_Connect();
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
		   cstmt.setString(3, value[2]);
		   cstmt.setInt(4, Integer.parseInt(value[3]));
		   cstmt.registerOutParameter(5, Types.VARCHAR);
		   cstmt.executeQuery();
		   
		   String[] getValue = null;
		   
		   if(cstmt.getString(5) != null) {
			   getValue = cstmt.getString(5).split(",");

			   for(int i=0;i<getValue.length;i++) {
				   String row[] = new String[column.length];
				   row[0]=getValue[i];
				   DtmStorage.addRow(row);
			   }
		   }else {
			   String row[] = new String[column.length];
			   row[0]="사양이 부족합니다.";
			   DtmStorage.addRow(row);
		   }
//		   System.out.println(cstmt.getString(5));
		   cstmt.close();	

	   }catch(SQLException e) { e.printStackTrace(); }
	   finally { try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
	   return DtmStorage;
   }
}
