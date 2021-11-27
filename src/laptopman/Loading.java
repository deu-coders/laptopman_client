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
		setResizable(false); // 크기 변경 불가능하도록 함
		setUndecorated(true); // 프레임의 타이틀바를 없앰
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 200);
			
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//
		
		Dimension frameSize = getSize();

		// 자신의 windowscreen 사이즈 측정

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

		// 출력해보면 두 사이즈가 출력되는걸 확인할 수 있다.

		System.out.println(frameSize + " " + windowSize);

		//설정할 위치에 (윈도우width-프레임width)/2, (윈도우height-프레임height)/2를 입력한다

		setLocation((windowSize.width - frameSize.width) / 2,(windowSize.height - frameSize.height) / 2);
		
		add(new ImagePanel("./image/Loading.gif"));
		
		setAlwaysOnTop(true);
		setVisible(true);
	}

}
