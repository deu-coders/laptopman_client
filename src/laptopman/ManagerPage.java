package laptopman;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import laptopman.components.DataManagementTab;
import laptopman.components.LoginForm;
import laptopman.utils.DB_Conn_Query;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ManagerPage extends JPanel {
	private LoginForm login = new LoginForm();
	private DataManagementTab tab = new DataManagementTab();
	public ManagerPage() {
		//백그라운드 설정
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{54, 54, 0};
		gridBagLayout.rowHeights = new int[]{95, 95, 95, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.anchor = GridBagConstraints.NORTH;
		gbc_login.insets = new Insets(0, 0, 5, 0);
		gbc_login.gridwidth = 2;
		gbc_login.gridx = 0;
		gbc_login.gridy = 0;
		add(login, gbc_login);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(tab, gbc_panel);
		
		tab.setVisible(false);
	}
	
	public DataManagementTab getTab() {
		return tab;
	}
	public LoginForm getLogin() {
		return login;
	}
}
