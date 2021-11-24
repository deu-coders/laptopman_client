package laptopman;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;

public class Main extends JFrame {

	private JPanel contentPane;
//	private DefaultTableModel DtmStorage;
	
	/**
	 * Launch the application.
	 */
	public void resizeColumnWidth(JTable table) { 
		final TableColumnModel columnModel = table.getColumnModel(); 
		for (int column = 0; column < table.getColumnCount(); column++) { 
			int width = 50; // Min width 
			for (int row = 0; row < table.getRowCount(); row++) { 
				TableCellRenderer renderer = table.getCellRenderer(row, column); 
				Component comp = table.prepareRenderer(renderer, row, column); 
				width = Math.max(comp.getPreferredSize().width +1 , width); 
			} 
			columnModel.getColumn(column).setPreferredWidth(width); 
		} 
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public void suitableProgram(JPanel Page, DB_Conn_Query dbc) {
		JTable table;
		
		Page.setLayout(null);
		Page.setBackground(Color.WHITE);
		Page.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label = new JLabel("CPU");
		label.setBounds(15, 15, 150, 20);
		Page.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(15, 35, 150, 20);
		Page.add(comboBox);
		
		String Combo_query="select CPU이름 from CPU";
		
		dbc.addComboBox(Combo_query,comboBox);
		
		JLabel GPU_label = new JLabel("GPU");
		GPU_label.setBounds(190, 15, 150, 20);
		Page.add(GPU_label);
		
		JComboBox GPU_comboBox = new JComboBox();
		GPU_comboBox.setBounds(190, 35, 150, 20);
		Page.add(GPU_comboBox);
		
		String GPU_Combo_query="select GPU이름 from GPU";
		
		dbc.addComboBox(GPU_Combo_query,GPU_comboBox);
		
		JLabel RAM_label = new JLabel("RAM");
		RAM_label.setBounds(365, 15, 150, 20);
		Page.add(RAM_label);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(365, 35, 150, 20);
		Page.add(spinner);
		
		RoundedButton btnNewButton = new RoundedButton("Search");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(535, 35, 150, 20);
		Page.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 261, 685, 205);
		Page.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
//		String query="select * from CPU";
//		String[] column = new String[] {"CPU이름", "아키텍쳐", "브랜드", "벤치마크점수", "벤치마크출처" }; // 컬럼 이름 설정
//		int[] type = new int[] {1,1,1,2,1};
		
		String query="select * from 제품정보";
		String[] column = new String[] {"제품정보ID","제품이름","이미지","브랜드","등록일","CPU이름","GPU이름","RAM","운영체제","화면크기","화면비율","해상도","무게"}; // 컬럼 이름 설정
		int[] type = new int[] {1,1,1,1,3,1,1,2,1,1,1,1,1};
		
		table = new JTable(dbc.sqlrun(query,column,type));
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setAutoCreateRowSorter(true);
		
		table.setCellSelectionEnabled(rootPaneCheckingEnabled);
		resizeColumnWidth(table);
		
		scrollPane.setViewportView(table);

	}
	
	public void specificationPage(JPanel Page,DB_Conn_Query dbc) {
		JTable table;
		JTable table_1;
		
		Page.setLayout(null);
		Page.setBackground(Color.WHITE);
		Page.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 261, 685, 205);
		Page.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
//		String query="select * from CPU";
//		String[] column = new String[] {"CPU이름", "아키텍쳐", "브랜드", "벤치마크점수", "벤치마크출처" }; // 컬럼 이름 설정
//		int[] type = new int[] {1,1,1,2,1};
		
		String query="select * from 제품정보";
		String[] column = new String[] {"제품정보ID","제품이름","이미지","브랜드","등록일","CPU이름","GPU이름","RAM","운영체제","화면크기","화면비율","해상도","무게"}; // 컬럼 이름 설정
		int[] type = new int[] {1,1,1,1,3,1,1,2,1,1,1,1,1};
		
		table = new JTable(dbc.sqlrun(query,column,type));
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setAutoCreateRowSorter(true);
		
		table.setCellSelectionEnabled(rootPaneCheckingEnabled);
		resizeColumnWidth(table);
		
		scrollPane.setViewportView(table);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(14, 57, 334, 151);
		Page.add(list);
		
		DefaultListModel listModel = new DefaultListModel();

		list.setModel(listModel);
		
		JScrollPane scrollPane_1 = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(362, 57, 337, 151);
		Page.add(scrollPane_1);
		
		table_1 = new JTable();
		
		String CPU_query="select * from 프로그램사양";
		String[] CPU_column = new String[] {"프로그램사양ID","프로그램이름","세부사항","CPU이름","GPU이름","RAM"}; // 컬럼 이름 설정
		int[] CPU_type = new int[] {2,1,1,1,1,2};
		
		table_1 = new JTable(dbc.sqlrun(CPU_query,CPU_column,CPU_type));
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table_1.setAutoCreateRowSorter(true);
		
		table_1.setCellSelectionEnabled(rootPaneCheckingEnabled);
		
		resizeColumnWidth(table_1);
//		table_1.getColumnModel().getColumn(0).setPreferredWidth(100); // 열크기 조절
	
		scrollPane_1.setViewportView(table_1);
		
		RoundedButton btnNewButton = new RoundedButton("Search");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(298, 221, 105, 27);
		Page.add(btnNewButton);
		
		table_1.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                JTable t = (JTable)e.getSource();
                if(e.getClickCount()==2) {
                    TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String s = String.format("%s (%s)", m.getValueAt(row, 0), m.getValueAt(row, 1));
//                        JOptionPane.showMessageDialog(t, s, "title", JOptionPane.INFORMATION_MESSAGE);
                        
                        boolean overlapCheck=false;
                        
                        for(Object j :listModel.toArray()) {
                        	if(j.toString().equals(s)) {
                        		overlapCheck=true;
                        		break;
                        	}
                        }
                        if(!overlapCheck)
                        	listModel.addElement(s);
                    }
                }
            }
        });
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            System.out.println(list.getSelectedValue());
		            listModel.remove(index);
		        } 
		    }
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DB_Conn_Query dbc = new DB_Conn_Query();
		
		dbc.getAdress(System.getProperty("adress"));
		dbc.getPort(System.getProperty("port"));
		dbc.getID(System.getProperty("id"));
		dbc.getPW(System.getProperty("pw"));
		dbc.DB_Conn();
		
		if (dbc.con == null) return;
		
		
		
		
		JPanel suitableProgram = new JPanel();
		
		createTabbedPane Pane = new createTabbedPane();
		Pane.addTab("메인",new JPanel());
		Pane.addTab("사양 체크",new JPanel());
		Pane.addTab("적합 프로그램 목록",suitableProgram);
		
		specificationPage(Pane.getPanel(1),dbc);
		suitableProgram(Pane.getPanel(2),dbc);
		
		JTabbedPane tabbedPane = Pane.getPane();
		tabbedPane.setBounds(0, 0, 730, 510);
		contentPane.add(tabbedPane);
		
	}
}
