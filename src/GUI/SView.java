package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class SView extends JFrame {

	private JFrame frmServer;
	private JTextArea textArea;

	
	public SView() {
		initialize();
		frmServer.setVisible(true);
	}

	
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 525, 506);
		frmServer.getContentPane().setBackground(new Color(204, 204, 204));
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		textArea.setBounds(10, 10,500, 490);
		frmServer.getContentPane().add(textArea);
	}

	public JFrame getFrmServer() {
		return frmServer;
	}

	public void setFrmServer(JFrame frmServer) {
		this.frmServer = frmServer;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

}
