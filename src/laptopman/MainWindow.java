package laptopman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.Array;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import laptopman.components.RoundedButton;
import laptopman.utils.DB_Conn_Query;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;


public class MainWindow extends JFrame{
	
	static boolean isStringEmpty(String str) {
		return str == null || str.isBlank();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoadingWindow loading = new LoadingWindow();

		try {
			System.out.println("Loading");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CompletableFuture<Boolean> future= new CompletableFuture<>();
		
		Executors.newCachedThreadPool().submit(() -> {
			try {
				System.out.println("Main");
				MainWindow frame = new MainWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		    future.complete(true);
		    return null;
		});

		try {
			if(future.get()==true)
				loading.setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	}
	

	public MainWindow()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 565);
		
		DB_Conn_Query dbc = new DB_Conn_Query();
		
		dbc.getAdress(System.getProperty("adress"));
		dbc.getPort(System.getProperty("port"));
		dbc.getID(System.getProperty("id"));
		dbc.getPW(System.getProperty("pw"));
		dbc.DB_Conn();
		
		var contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		var tabs = new JTabbedPane(JTabbedPane.TOP);
	
		JPanel panel = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabs.addTab("Main",new MainPage() {{
			JScrollPane scrollPane = getmainScrollPane();
			String query="select * from 제품정보";
			String[] column = new String[] {"제품정보ID","제품이름","이미지","브랜드","등록일","CPU이름","GPU이름","RAM","운영체제","화면크기","화면비율","해상도","무게"}; // 컬럼 이름 설정
			int[] type = new int[] {1,1,1,1,3,1,1,2,1,1,1,1,1};
			DefaultTableModel model = dbc.sqlrun(query,column,type);
			JTable table = new JTable(model);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			table.setAutoCreateRowSorter(true);
			
			resizeColumnWidth(table);
			
			table.setRowHeight(60);
			
			TableColumn tableColumn = table.getColumn("이미지");
			tableColumn.setMaxWidth(60);
			tableColumn.setMinWidth(60);
			
			// https://stackoverflow.com/questions/4941372/how-to-insert-image-into-jtable-cell
			tableColumn.setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public void setValue(Object value) {
					if (value == null) {
						setText("");
					}
					else {
						URL url = null;
						BufferedImage img = null;
						
						try {
							url = new URL((String) value);
							img = ImageIO.read(url);

							setIcon(new ImageIcon(resizeImage(img, 60, 60)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			scrollPane.setViewportView(table);
		}});
		
		tabs.addTab("다중프로그램적합목록",new MultiplesuitablePage(dbc) {{
			JScrollPane scrollPane = getScrollPane();
			JScrollPane datascrollPane = getdataScrollPane();
			String Program_query="select * from 프로그램사양";
			String[] Program_column = new String[] {"프로그램사양ID","프로그램이름","세부사항","CPU이름","GPU이름","RAM"}; // 컬럼 이름 설정
			int[] Program_type = new int[] {2,1,1,1,1,2};
			DefaultListModel listModel=getlistModel();
			RoundedButton serch_Button = getlistButton();
			
			JTable table_1 = new JTable(dbc.sqlrun(Program_query,Program_column,Program_type));
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			table_1.setAutoCreateRowSorter(true);
			resizeColumnWidth(table_1);
			scrollPane.setViewportView(table_1);
			
			table_1.addMouseListener(new MouseAdapter() {
	            @Override public void mouseClicked(MouseEvent e) {
	                JTable t = (JTable)e.getSource();
	                if(e.getClickCount()==2) {
	                    TableModel m = t.getModel();
	                    Point pt = e.getPoint();
	                    int i = t.rowAtPoint(pt);
	                    if(i>=0) {
	                        int row = t.convertRowIndexToModel(i);
	                        String s = String.format("[%s] %s(%s)", m.getValueAt(row, 0),m.getValueAt(row, 1),m.getValueAt(row, 2));
//	                        JOptionPane.showMessageDialog(t, s, "title", JOptionPane.INFORMATION_MESSAGE);
	                        
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
			
			
			serch_Button.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					String callable_query ="{call SP_다중프로그램적합노트북목록(?,?)}";

					String callable_value = getlistString();
					String[] callable_column = new String[] {"제품정보ID","제품이름","CPU이름","GPU이름","RAM"};
					JTable table = new JTable();
					DefaultTableModel model = dbc.multiplesuitable_callable(callable_query, callable_value, callable_column);

					table.setModel(model);
			
					table.setAutoCreateRowSorter(true);
					
					datascrollPane.setViewportView(table);
				}
			});
			
			
		}});
/////////////////////////////////////////////////////////////////////////////
		tabs.addTab("적합프로그램목록",new SuitablePage() {{
			JComboBox CPU_ComboBox=getCPUComboBox();
			JComboBox GPU_ComboBox=getGPUComboBox();
			JSpinner RAM_Spinner=getRAMSpinner();
			RoundedButton suit_Search=getsuitSearch();
			JScrollPane suitScroll= getsuitablePageScrollPane();
			String Combo_query="select CPU이름 from CPU";
			
			dbc.addComboBox(Combo_query,CPU_ComboBox);
			
			String GPU_Combo_query="select GPU이름 from GPU";
			
			dbc.addComboBox(GPU_Combo_query,GPU_ComboBox);
			
			suit_Search.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					String callable_query ="{call SP_적합프로그램목록(?, ?, ?, ?)}";
					String[] callable_value = new String[] {CPU_ComboBox.getSelectedItem().toString() , GPU_ComboBox.getSelectedItem().toString() , RAM_Spinner.getValue().toString()};
					String[] callable_column = new String[] {"프로그램이름","세부사항","적합"};
					System.out.println(CPU_ComboBox.getSelectedItem().toString()+",\t"+GPU_ComboBox.getSelectedItem().toString()+",\t"+RAM_Spinner.getValue().toString());
					
					JTable table = new JTable();
					
					TableModel model = model = dbc.sql_callable(callable_query, callable_value, callable_column);

					table.setModel(model);
			
					table.setAutoCreateRowSorter(true);
					
					table.setCellSelectionEnabled(rootPaneCheckingEnabled);

					suitScroll.setViewportView(table);
				}
			});
			
		}});
/////////////////////////////////////////////////////////////////////////////
		tabs.addTab("사양판정",new SpecificationPage() {{
			JComboBox Program_ComboBox=getProgramComboBox();
			JComboBox CPU_ComboBox=getsuitablePage().getCPUComboBox();
			JComboBox GPU_ComboBox=getsuitablePage().getGPUComboBox();
			JSpinner RAM_Spinner=getsuitablePage().getRAMSpinner();
			RoundedButton suit_Search=getsuitablePage().getsuitSearch();
			JScrollPane suitScroll= getsuitablePage().getsuitablePageScrollPane();
			
			String Program_Combo_query="select 프로그램이름 from 프로그램";
			
			dbc.addComboBox(Program_Combo_query,Program_ComboBox);
			
			String Combo_query="select CPU이름 from CPU";
			
			dbc.addComboBox(Combo_query,CPU_ComboBox);
			
			String GPU_Combo_query="select GPU이름 from GPU";
			
			dbc.addComboBox(GPU_Combo_query,GPU_ComboBox);
			
			suit_Search.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					String callable_query ="{call SP_사양판정(?, ?, ?, ?, ?)}";
					String[] callable_value = new String[] {Program_ComboBox.getSelectedItem().toString() , CPU_ComboBox.getSelectedItem().toString() , GPU_ComboBox.getSelectedItem().toString() , RAM_Spinner.getValue().toString()};
					String[] callable_column = new String[] {"적합 사양"};
					System.out.println(Program_ComboBox.getSelectedItem().toString()+",\t"+CPU_ComboBox.getSelectedItem().toString()+",\t"+GPU_ComboBox.getSelectedItem().toString()+",\t"+RAM_Spinner.getValue().toString());
					TableModel model = dbc.specification_callable(callable_query, callable_value, callable_column);

					JTable table = new JTable();
					
					table.setModel(model);
			
					table.setAutoCreateRowSorter(true);
					
					table.setCellSelectionEnabled(rootPaneCheckingEnabled);

					suitScroll.setViewportView(table);
				}
			});
		}});
/////////////////////////////////////////////////////////////////////////////
		tabs.addTab("관리자",new ManagerPage()
		{{
			String Table_query="select TABLE_NAME from USER_TABLES";
			JComboBox insert_ComboBox=getTab().getInsert().getComboBox();
			JScrollPane insert_ScrollPane=getTab().getInsert().getScrollPane();
			RoundedButton insert_Button=getTab().getInsert().getButton();

			JComboBox delete_ComboBox=getTab().getDelete().getComboBox();
			JScrollPane delete_ScrollPane=getTab().getDelete().getScrollPane();
			RoundedButton delete_Button=getTab().getDelete().getButton();
			
			dbc.addComboBox(Table_query,insert_ComboBox);
			dbc.addComboBox(Table_query,delete_ComboBox);
			
			RoundedButton login_Button = getLogin().getButton();
			JTextField login_id = getLogin().getid();
			JPasswordField login_pw = getLogin().getpw();
			
			login_Button.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					if(dbc.manager_Login(login_id.getText().toString(),login_pw.getText().toString())) {
						getTab().setVisible(true);
					}
				}
			});
			
			insert_ComboBox.addItemListener(new ItemListener() {
				@Override
			    public void itemStateChanged(ItemEvent event) {
			       if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			          
			          if(item.toString().isEmpty())
			        	  return;

			          dbc.get_column(item.toString());
			          
			          String[] column= dbc.get_column(item.toString());// 컬럼 이름 설정
			  		
			          DefaultTableModel DtmStorage = new DefaultTableModel(column, 0){
			 			public boolean isCellEditable(int row, int column){ // 테이블을 더블클릭할 때 수정여부 설정
			 				return true;    // 셀 수정 가능(return true), 불가능 설정(return false)
			 			}
			 		 };
			 		 
			 		 DtmStorage.setColumnIdentifiers(column);
			 		 
			 		 for(int i=0;i<5;i++)
			 			 DtmStorage.addRow(new String[column.length]);

			 		JTable insert_table = new JTable(DtmStorage);
			 		
			 		insert_table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					
					insert_table.setCellSelectionEnabled(rootPaneCheckingEnabled);
	
					insert_ScrollPane.setViewportView(insert_table);
					
			       }
			    }  
			});			
			
			insert_Button.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					String[] column=dbc.get_column(insert_ComboBox.getSelectedItem().toString());

					JViewport viewport = insert_ScrollPane.getViewport(); 
					JTable mytable = (JTable)viewport.getView();
					
					ArrayList<String[]> rows = new ArrayList<String[]>();
					
					for(int j=0;j<5;j++) {
						if(isStringEmpty((String)mytable.getModel().getValueAt(j, 0)))
							continue;
						
						String[] row = new String[column.length];
						
						for(int i=0;i<column.length;i++) {
							row[i]=(String) mytable.getModel().getValueAt(j, i);
						}
						rows.add(row);
					}
					
					try {
						dbc.insert_data(insert_ComboBox.getSelectedItem().toString(), rows);
						System.out.println("Insert Success");
						JOptionPane.showMessageDialog(null, "Insert Success");
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Insert Error : "+e1,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						System.out.println("Insert Error : "+e1);
						return;
					}
				}
			});
			
			delete_ComboBox.addItemListener(new ItemListener() {
				@Override
			    public void itemStateChanged(ItemEvent event) {
			        if (event.getStateChange() == ItemEvent.SELECTED) {
			            Object item = event.getItem();
			          
			        if(item.toString().isEmpty())
			        	return;
			          
			        String query="select * from "+delete_ComboBox.getSelectedItem().toString();
			  		String[] column = dbc.get_column(delete_ComboBox.getSelectedItem().toString()); // 컬럼 이름 설정
			  		int[] type = dbc.get_type(delete_ComboBox.getSelectedItem().toString());
			  		
			  		JTable table = new JTable(dbc.sqlrun(query,column,type));
			  		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			  		
			  		table.setAutoCreateRowSorter(true);
			  		
			  		resizeColumnWidth(table);
			  		
			  		delete_ScrollPane.setViewportView(table);

			       }
			    }  
			});
			
			delete_Button.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
				@Override
				public void actionPerformed(ActionEvent e){
					JViewport viewport = delete_ScrollPane.getViewport(); 
					JTable table = (JTable)viewport.getView();
					int[] Row = table.getSelectedRows();
					
					TableModel m = table.getModel();
					String table_name = delete_ComboBox.getSelectedItem().toString();
					String[] querys = new String[Row.length];
					for(int i=0;i<Row.length;i++) {
	                    querys[i] = dbc.get_delete_query(table_name,Row[i],m);
					}
					
					try {
						dbc.delete_data(querys);
						JOptionPane.showMessageDialog(null, "Insert Success");
						System.out.println("Delete Success");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Insert Error : "+e1,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
						System.out.println("Delete Error"+e1);
					}
				}
			});
			
			insert_ComboBox.setSelectedIndex(1);
			delete_ComboBox.setSelectedIndex(1);
			
		}});
		


		contentPane.add(tabs);

		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

		//설정할 위치에 (윈도우width-프레임width)/2, (윈도우height-프레임height)/2를 입력한다
		setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);
		setVisible(true);
		toFront();
	}
	
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
	
	public JPanel specificationPage(JPanel Page, DB_Conn_Query dbc) {
		JTable table= new JTable();
		
		Page.setLayout(null);
		Page.setBackground(Color.WHITE);
		Page.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 142, 685, 324);
		Page.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel CPU_label = new JLabel("CPU");
		CPU_label.setBounds(15, 67, 150, 20);
		Page.add(CPU_label);
		
		JComboBox CPU_comboBox = new JComboBox();
		CPU_comboBox.setBounds(15, 87, 150, 20);
		Page.add(CPU_comboBox);
		
		String CPU_query="select CPU이름 from CPU";
		
		dbc.addComboBox(CPU_query,CPU_comboBox);
		
		JLabel GPU_label = new JLabel("GPU");
		GPU_label.setBounds(190, 67, 150, 20);
		Page.add(GPU_label);
		
		JComboBox GPU_comboBox = new JComboBox();
		GPU_comboBox.setBounds(190, 87, 150, 20);
		Page.add(GPU_comboBox);
		
		String GPU_Combo_query="select GPU이름 from GPU";
		
		dbc.addComboBox(GPU_Combo_query,GPU_comboBox);
		
		JLabel RAM_label = new JLabel("RAM");
		RAM_label.setBounds(365, 67, 150, 20);
		Page.add(RAM_label);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(365, 87, 150, 20);
		spinner.setValue(8192);
		Page.add(spinner);
		
		JLabel Program_label = new JLabel("Program");
		Program_label.setBounds(14, 15, 150, 20);
		Page.add(Program_label);
		
		JComboBox Program_comboBox = new JComboBox();
		Program_comboBox.setBounds(14, 35, 150, 20);
		Page.add(Program_comboBox);


		String Program_query="select 프로그램이름 from 프로그램";
		
		dbc.addComboBox(Program_query,Program_comboBox);
		
		RoundedButton btnNewButton = new RoundedButton("Search");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(547, 87, 150, 20);
		
		btnNewButton.addActionListener(new ActionListener(){ //익명클래스로 리스너 작성
			@Override
			public void actionPerformed(ActionEvent e){
//				JOptionPane.showMessageDialog(null, "알림");

				String callable_query ="{call SP_사양판정(?, ?, ?, ?, ?)}";
				String[] callable_value = new String[] {Program_comboBox.getSelectedItem().toString() , CPU_comboBox.getSelectedItem().toString() , GPU_comboBox.getSelectedItem().toString() , spinner.getValue().toString()};
				String[] callable_column = new String[] {"적합 사양"};
				System.out.println(Program_comboBox.getSelectedItem().toString()+",\t"+CPU_comboBox.getSelectedItem().toString()+",\t"+GPU_comboBox.getSelectedItem().toString()+",\t"+spinner.getValue().toString());
				TableModel model = dbc.specification_callable(callable_query, callable_value, callable_column);

				table.setModel(model);
		
				table.setAutoCreateRowSorter(true);
				
				table.setCellSelectionEnabled(rootPaneCheckingEnabled);

				scrollPane.setViewportView(table);
			}
		});
		
		Page.add(btnNewButton);
		
		return Page;
	}


}
