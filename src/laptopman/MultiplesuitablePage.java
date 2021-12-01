package laptopman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import laptopman.components.RoundedButton;
import laptopman.utils.DB_Conn_Query;
import laptopman.utils.resizeColumnWidth;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class MultiplesuitablePage extends JPanel implements resizeColumnWidth{
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPane_1;
	private JList list = new JList();
	private DefaultListModel listModel = new DefaultListModel();
	private JPanel panel;
	private JScrollPane scrollPane_2;
	private JList list_1;
	private RoundedButton btnNewButton = new RoundedButton("Search");
	
	
	public MultiplesuitablePage(DB_Conn_Query dbc) {
		
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{590, 0};
		gridBagLayout.rowHeights = new int[]{161, 23, 161, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.add(list);
		panel.add(scrollPane_2);
		
		
//		panel.add(list);
		panel.add(scrollPane);
		
		list.setModel(listModel);
		scrollPane_2.setViewportView(list);
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            System.out.println(list.getSelectedValue());
		            listModel.remove(index);
		        } 
		    }
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		
				scrollPane_1 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
				gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_1.gridx = 0;
				gbc_scrollPane_1.gridy = 2;
				add(scrollPane_1, gbc_scrollPane_1);
		
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JScrollPane getdataScrollPane() {
		return scrollPane_1;
	}
	
	public DefaultListModel getlistModel() {
		return listModel;
	}
	public RoundedButton getlistButton() {
		return btnNewButton;
	}
	public String getlistString() {
		String[] l=new String[listModel.getSize()];
		
		int index=0;
		for(Object s :listModel.toArray()) {
			l[index]=(s.toString().split("\\[")[1]).split("\\]")[0];
			index++;
		}
		String ls = String.join(",", l);
		System.out.println(ls);
		return ls;
	}

	
}
