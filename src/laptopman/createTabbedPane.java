package laptopman;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class createTabbedPane {
	private JTabbedPane pane;
	private ArrayList<JPanel> Panel;
	
	public createTabbedPane() {
		pane = new JTabbedPane();
		Panel = new ArrayList<>();
	}
	
	public void addTab(String Name,JPanel Page) {
		Panel.add(Page);
		pane.addTab(Name, null, Page, null);
	}
	
	public JTabbedPane getPane() {
		return pane;
	}
	
	public JPanel getPanel(int index) {
		return Panel.get(index);
	}
}
