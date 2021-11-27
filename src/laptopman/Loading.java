package laptopman;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;

public class Loading extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		new Loading();
	}

	/**
	 * Create the frame.
	 */
	public Loading() {
		setResizable(false); // ũ�� ���� �Ұ����ϵ��� ��
		setUndecorated(true); // �������� Ÿ��Ʋ�ٸ� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 200);
			
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//
		
		Dimension frameSize = getSize();

		// �ڽ��� windowscreen ������ ����

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

		// ����غ��� �� ����� ��µǴ°� Ȯ���� �� �ִ�.

		System.out.println(frameSize + " " + windowSize);

		//������ ��ġ�� (������width-������width)/2, (������height-������height)/2�� �Է��Ѵ�

		setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);
		
		add(new ImagePanel("./image/Loading.gif"));
		
		setAlwaysOnTop(true);
		setVisible(true);
	}

}
