package laptopman.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import laptopman.utils.DB_Conn_Query;

public class LoginForm extends JPanel {
	private Border logintitle = BorderFactory.createTitledBorder("Login");
	private Border loginEmpty = BorderFactory.createEmptyBorder(10 , 10 , 10 , 10);
	private CompoundBorder loginBorder = new CompoundBorder(logintitle, loginEmpty);
	private JTextField id_TextField = new JTextField(System.getProperty("id")+"_TEST");
	private JPasswordField pw_TextField = new JPasswordField(System.getProperty("pw"));
	private RoundedButton login_Button = new RoundedButton();
	private GridBagLayout gbl_login = new GridBagLayout();
	private DB_Conn_Query dbc = new DB_Conn_Query();
	private JTabbedPane tabs = null;

	public LoginForm() {
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBorder(loginBorder);
		
		gbl_login.columnWidths = new int[]{116, 116, 0};
		gbl_login.rowHeights = new int[]{23, 23, 0};
		gbl_login.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_login.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_login);
		
		GridBagConstraints gbc_id_TextField = new GridBagConstraints();
		gbc_id_TextField.fill = GridBagConstraints.BOTH;
		gbc_id_TextField.insets = new Insets(5, 0, 0, 5);
		gbc_id_TextField.gridx = 0;
		gbc_id_TextField.gridy = 0;
		add(id_TextField, gbc_id_TextField);
		id_TextField.setColumns(10);
		
		login_Button.setText("Login");
		login_Button.setForeground(Color.WHITE);
		login_Button.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints gbc_login_Button = new GridBagConstraints();
		gbc_login_Button.gridheight = 2;
		gbc_login_Button.fill = GridBagConstraints.BOTH;
		gbc_login_Button.insets = new Insets(5, 5, 0, 0);
		gbc_login_Button.gridx = 1;
		gbc_login_Button.gridy = 0;
		add(login_Button, gbc_login_Button);
		
		GridBagConstraints gbc_pw_TextField = new GridBagConstraints();
		gbc_pw_TextField.fill = GridBagConstraints.BOTH;
		gbc_pw_TextField.insets = new Insets(5, 0, 0, 5);
		gbc_pw_TextField.gridx = 0;
		gbc_pw_TextField.gridy = 1;
		add(pw_TextField, gbc_pw_TextField);
		pw_TextField.setColumns(10);

	}
	
	public RoundedButton getButton() {
		return login_Button;
	}
	public JTextField getid() {
		return id_TextField;
	}
	public JPasswordField getpw() {
		return pw_TextField;
	}
}
