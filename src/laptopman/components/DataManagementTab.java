package laptopman.components;

import javax.swing.JTabbedPane;


import laptopman.DataManagementTabPage;
import laptopman.utils.DB_Conn_Query;

public class DataManagementTab extends JTabbedPane {
	private DataManagementTabPage insert = new DataManagementTabPage("insert");
	private DataManagementTabPage delete = new DataManagementTabPage("delete");
	DB_Conn_Query dbc = new DB_Conn_Query();
	public DataManagementTab() {
		addTab("insert",insert);
		addTab("delete",delete);
		setVisible(true);
	}
	
	public DataManagementTabPage getInsert() {
		return insert;
	}
	
	public DataManagementTabPage getDelete() {
		return delete;
	}
}
