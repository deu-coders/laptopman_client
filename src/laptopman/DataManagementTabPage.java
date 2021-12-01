package laptopman;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import laptopman.components.RoundedButton;
import laptopman.utils.DB_Conn_Query;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;


public class DataManagementTabPage extends JPanel {
	private JTable data_table = new JTable();
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private JLabel table_Label = new JLabel("Table");
	private JComboBox table_comboBox = new JComboBox();
	private JLabel data_Label = new JLabel("Data");
	private RoundedButton btnNewButton = new RoundedButton();
	private DB_Conn_Query dbc = new DB_Conn_Query();
	private JScrollPane table_scrollPane = new JScrollPane();
	
	public DataManagementTabPage(String button_Text) {
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_table_Label = new GridBagConstraints();
		gbc_table_Label.anchor = GridBagConstraints.WEST;
		gbc_table_Label.fill = GridBagConstraints.VERTICAL;
		gbc_table_Label.insets = new Insets(10, 10, 10, 10);
		gbc_table_Label.gridx = 0;
		gbc_table_Label.gridy = 0;
		add(table_Label, gbc_table_Label);
		
		GridBagConstraints gbc_table_comboBox = new GridBagConstraints();
		gbc_table_comboBox.insets = new Insets(0, 10, 10, 10);
		gbc_table_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_table_comboBox.gridx = 0;
		gbc_table_comboBox.gridy = 1;
		add(table_comboBox, gbc_table_comboBox);
		
		btnNewButton.setText(button_Text);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(10, 10, 10, 10);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		GridBagConstraints gbc_data_Label = new GridBagConstraints();
		gbc_data_Label.anchor = GridBagConstraints.WEST;
		gbc_data_Label.insets = new Insets(10, 10, 5, 10);
		gbc_data_Label.gridx = 0;
		gbc_data_Label.gridy = 2;
		add(data_Label, gbc_data_Label);
		
		GridBagConstraints gbc_data_table = new GridBagConstraints();
		gbc_data_table.insets = new Insets(10, 10, 10, 10);
		gbc_data_table.gridwidth = 3;
		gbc_data_table.fill = GridBagConstraints.BOTH;
		gbc_data_table.gridx = 0;
		gbc_data_table.gridy = 3;
		add(table_scrollPane, gbc_data_table);
		
		
	}
	
	public JComboBox getComboBox() {
		return table_comboBox;
	}
	public JScrollPane getScrollPane() {
		return table_scrollPane;
	}
	public RoundedButton getButton() {
		return btnNewButton;
	}
}
