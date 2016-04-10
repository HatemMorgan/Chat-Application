package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientStartWindow {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textField_1;
	private JButton btnSubmit;
	
	public ClientStartWindow() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(28, 34, 450, 300);
		frame.getContentPane().add(panel);
		
		Label label = new Label("Please Enter Your Name");
		label.setBounds(10,43,200,30);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setAlignment(Label.CENTER);
		label.setBackground(new Color(51, 51, 51));
		label.setForeground(Color.LIGHT_GRAY);
		panel.add(label);
		
		Label label2 = new Label("Please Enter Your Password");
		label2.setBounds(10,104,219,30);
		label2.setFont(new Font("Arial", Font.BOLD, 16));
		label2.setAlignment(Label.CENTER);
		label2.setBackground(new Color(51, 51, 51));
		label2.setForeground(Color.LIGHT_GRAY);
		panel.add(label2);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(269, 43, 116, 30);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(269, 104, 116, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		 btnSubmit = new JButton("connect");
		btnSubmit.setBounds(174, 192, 97, 25);
		panel.add(btnSubmit);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

}
