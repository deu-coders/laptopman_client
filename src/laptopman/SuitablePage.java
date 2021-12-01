package laptopman;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import laptopman.components.RoundedButton;

import javax.swing.JLabel;

public class SuitablePage extends JPanel {
	private RoundedButton Search_Button = new RoundedButton("Search");
	private JComboBox CPU_comboBox = new JComboBox();
	private JComboBox GPU_comboBox = new JComboBox();
	private JSpinner RAM_spinner = new JSpinner();
	private JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_1 = new JPanel();
	
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	
	public SuitablePage() {
		setBackground(Color.WHITE);
		
		CPU_comboBox.setPreferredSize(new Dimension(150,20));
		GPU_comboBox.setPreferredSize(new Dimension(150,20));
		RAM_spinner.setPreferredSize(new Dimension(150,20));
		Search_Button.setPreferredSize(new Dimension(150,20));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel_1.setBackground(Color.WHITE);
		panel_2.setBackground(Color.WHITE);
		panel_3.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(10, 10, 10, 10);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{49, 0};
		gbl_panel_1.rowHeights = new int[]{23, 23, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{52, 0};
		gbl_panel_2.rowHeights = new int[]{23, 23, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("GPU");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 2;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_11);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{27, 0};
		gbl_panel_3.rowHeights = new int[]{22, 22, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		Search_Button.setForeground(Color.WHITE);
		Search_Button.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints gbc_Search_Button = new GridBagConstraints();
		gbc_Search_Button.insets = new Insets(0, 0, 5, 0);
		gbc_Search_Button.gridheight = 3;
		gbc_Search_Button.fill = GridBagConstraints.BOTH;
		gbc_Search_Button.gridx = 3;
		gbc_Search_Button.gridy = 0;
		panel.add(Search_Button, gbc_Search_Button);
		
		JLabel lblNewLabel = new JLabel("CPU");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel1.gridx = 0;
		gbc_lblNewLabel1.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel1);
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.gridheight = 2;
		gbc_lblNewLabel_2_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1.gridy = 0;
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panel.add(panel_1, gbc_panel_1);
		
		GridBagConstraints gbc_CPU_comboBox_1 = new GridBagConstraints();
		gbc_CPU_comboBox_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_CPU_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_CPU_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_CPU_comboBox_1.gridx = 2;
		gbc_CPU_comboBox_1.gridy = 0;
		GridBagConstraints gbc_CPU_comboBox_11 = new GridBagConstraints();
		gbc_CPU_comboBox_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_CPU_comboBox_11.gridx = 0;
		gbc_CPU_comboBox_11.gridy = 1;
		panel_1.add(CPU_comboBox, gbc_CPU_comboBox_11);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.SOUTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		
		
		GridBagConstraints gbc_GPU_comboBox = new GridBagConstraints();
		gbc_GPU_comboBox.gridheight = 2;
		gbc_GPU_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_GPU_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_GPU_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_GPU_comboBox.gridx = 1;
		gbc_GPU_comboBox.gridy = 0;
		GridBagConstraints gbc_GPU_comboBox1 = new GridBagConstraints();
		gbc_GPU_comboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_GPU_comboBox1.gridy = 1;
		panel_2.add(GPU_comboBox, gbc_GPU_comboBox1);
		
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 2;
		panel.add(panel_3, gbc_panel_3);
		GridBagConstraints gbc_RAM_spinner_1 = new GridBagConstraints();
		gbc_RAM_spinner_1.gridheight = 2;
		gbc_RAM_spinner_1.anchor = GridBagConstraints.SOUTH;
		gbc_RAM_spinner_1.fill = GridBagConstraints.VERTICAL;
		gbc_RAM_spinner_1.insets = new Insets(0, 0, 0, 5);
		gbc_RAM_spinner_1.gridx = 0;
		gbc_RAM_spinner_1.gridy = 1;
		
		JLabel lblNewLabel_2 = new JLabel("RAM");
		GridBagConstraints gbc_lblNewLabel_2_1_11 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_1_11.gridx = 0;
		gbc_lblNewLabel_2_1_11.gridy = 0;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2_1_11);
		
		RAM_spinner.setValue(8192);
		GridBagConstraints gbc_RAM_spinner_11 = new GridBagConstraints();
		gbc_RAM_spinner_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_RAM_spinner_11.gridx = 0;
		gbc_RAM_spinner_11.gridy = 1;
		panel_3.add(RAM_spinner, gbc_RAM_spinner_11);
		
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
	}
	public JScrollPane getsuitablePageScrollPane() {
		return scrollPane;
	}
	public JComboBox getCPUComboBox() {
		return CPU_comboBox;
	}
	public JComboBox getGPUComboBox() {
		return GPU_comboBox;
	}
	public JSpinner getRAMSpinner() {
		return RAM_spinner;
	}
	public RoundedButton getsuitSearch() {
		return Search_Button;
	}
}
