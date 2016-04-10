package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.Label;

import javax.swing.JLabel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;


public class CView extends JFrame{

	private JFrame frmChatapp;
	private JPanel OnlineUsersList;
	private Label label_OnlineUsers;
	private JTextArea textArea;
	private JTextField textField;
	private JButton btnSend;
	private JButton btnDisconnect;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
;

	
	public CView() {
		initialize();
		frmChatapp.setVisible(true);
	}

	
	private void initialize() {
		frmChatapp = new JFrame();
		frmChatapp.getContentPane().setBackground(new Color(204, 204, 204));
		frmChatapp.getContentPane().setLayout(null);
		frmChatapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		OnlineUsersList = new JPanel();
		OnlineUsersList.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		OnlineUsersList.setBounds(434, 39, 185, 496);
		OnlineUsersList.setLayout(null);
		frmChatapp.getContentPane().add(OnlineUsersList);

	
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		textArea.setBounds(12, 39, 374, 421);
		frmChatapp.getContentPane().add(textArea);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(171, 173, 179)));
      textField.setFont(new Font("Arial Black", Font.PLAIN, 14));
		textField.setBounds(12, 490, 299, 50);
		frmChatapp.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnSend.setBounds(323, 497, 86, 31);
		frmChatapp.getContentPane().add(btnSend);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(434, 13, 173, 25);
		btnDisconnect.setFont(new Font("Arial Black", Font.PLAIN, 14));
		frmChatapp.getContentPane().add(btnDisconnect);

	


		frmChatapp.setBackground(Color.LIGHT_GRAY);
		frmChatapp.setTitle("ChatApp");
		frmChatapp.setBounds(100, 100, 637, 600);
		frmChatapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 label_OnlineUsers = new Label("Online Users");
		label_OnlineUsers.setPreferredSize(new Dimension(185, 50));
		label_OnlineUsers.setSize(new Dimension(185, 50));
		label_OnlineUsers.setMaximumSize(new Dimension(185, 50));
		label_OnlineUsers.setFont(new Font("Arial", Font.BOLD, 16));
		label_OnlineUsers.setAlignment(Label.CENTER);
		label_OnlineUsers.setBackground(new Color(51, 51, 51));
		label_OnlineUsers.setForeground(Color.LIGHT_GRAY);
		OnlineUsersList.add(label_OnlineUsers);
		
	
		
		
	}

	public JFrame getFrmChatapp() {
		return frmChatapp;
	}

	public void setFrmChatapp(JFrame frmChatapp) {
		this.frmChatapp = frmChatapp;
	}

	public JPanel getOnlineUsersList() {
		return OnlineUsersList;
	}

	public Label getLabel_OnlineUsers() {
		return label_OnlineUsers;
	}

	public void setLabel_OnlineUsers(Label label_OnlineUsers) {
		this.label_OnlineUsers = label_OnlineUsers;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}

	public JButton getbtnDisconnect() {
		return btnDisconnect;
	}
	
	public static void main(String[] args) {
		CView c= new CView();
	}
}
