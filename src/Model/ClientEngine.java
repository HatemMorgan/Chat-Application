package Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import GUI.CView;
import GUI.ClientStartWindow;
import GUI.OnlineUsersButtons;

public class ClientEngine implements ActionListener {

	ClientStartWindow startWindow;
	private String Name;
	private String ID;
	private Socket clientSocket;
	private DataInputStream din;
	private DataOutputStream dout;
	private String CurrentChatID;
	static ArrayList<OnlineUsersInformations> onlineUsersList;
	boolean connnected=false;
	
	
	
	
	
	public ClientEngine() throws UnknownHostException, IOException{
		
		
		startWindow= new ClientStartWindow();
		onlineUsersList=new ArrayList<OnlineUsersInformations>();
		 clientSocket = new Socket("127.0.0.1", 5000);
			dout=new DataOutputStream(clientSocket.getOutputStream());
			   din= new DataInputStream(clientSocket.getInputStream());
			   
		startWindow.getBtnSubmit().addActionListener(new ActionListener() {
	     
			@Override
			public void actionPerformed(ActionEvent e) {

				if(!startWindow.getTextFieldName().getText().isEmpty()){
					Name= startWindow.getTextFieldName().getText();
					IntializeClientSocket();
					if(connnected){
					
					startWindow.getFrame().setVisible(false);
					}
				
				
				
	
			 
			}
			else{
			
				JOptionPane.showMessageDialog(null,"Please enter Your Name and Password");
			}
			}
		});
	
	}
     private void IntializeClientSocket(){
    	 
    	 try {

			dout.writeUTF(Name+":wantto"+":connect");
			//String s= din.readUTF();
	
            ID=din.readUTF();
            if(!(ID.equals("-1")))
            {
            	connnected=true;
            Thread CLientThread= new Thread(new ThreadedClent(din, dout,Name));
            CLientThread.start();
            }else{
            	JOptionPane.showMessageDialog(null,"You cannot connect to the server Please try again with different name");
            }
		} catch (Exception e) {
	      
		}
    	
    	 
     }
     

 	@Override
 	public void actionPerformed(ActionEvent e) {
 
 }
 	
 	// --------------------------------------------------------//
 	
 	public class ThreadedClent implements Runnable,ActionListener {
 		  DataInputStream din;
 		  DataOutputStream dout;

 		  CView ClientWindow;
 		  
 		   public ThreadedClent(DataInputStream din, DataOutputStream dout,String Name) {
 		      this.din = din;
 		      this.dout = dout;
 		      ClientWindow=new CView();
 				ClientWindow.getFrmChatapp().setTitle(Name);
 				ClientWindow.getFrmChatapp().setName(ID);
 				 ClientWindow.getBtnSend().setEnabled(true);
				 ClientWindow.getTextField().setEditable(true);
				 ClientWindow.getTextArea().setEditable(true);
 				ClientWindow.getBtnSend().addActionListener(new ActionListener() {
 					
 					@Override
 					public void actionPerformed(ActionEvent e) {
 						SendMessage(CurrentChatID);
 					}
 				});
 				
 				ClientWindow.getbtnDisconnect().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
					String s= ((JButton)arg0.getSource()).getText();
					if(s.equals("Disconnect")){
					   try {
						Disconnect();
					} catch (IOException e) {
					ClientWindow.getTextArea().append("Cannot disconnect \n");
						e.printStackTrace();
					}
					}else{
						
						//IntializeClientSocket();
						ClientWindow.getFrmChatapp().setVisible(false);
						try {
							ClientEngine c= new ClientEngine();
						
						} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"COnncetion failed try again");
							e.printStackTrace();
						}
						
						
						
					}
					}
				});

 		}
 		  
 		 
 		  
 			@Override
 			public void run() {
 		     try{
 		    	 String stream;
 		    	 String [] Data;
 		    	 
 		     while((stream = din.readUTF())!=null){
 	 
 		    	 if(stream.charAt(0)=='1'){
 		    	
 		    		 ViewconnectedClients(stream);
 		    	 
 		    	 }else{
 		    	
 		    		 Data= stream.split(":");
 		    		 if(Data[2].equals("chat")){
                   
 		    			 ClientWindow.getTextArea().append(Data[0]+" : " + Data[1]+"\n");
 		    			ClientWindow.getTextArea().setCaretPosition(ClientWindow.getTextArea().getDocument().getLength());

 		    		 }else{
 		    			 ClientWindow.getTextArea().append("Nothing match \n"); 
 		    			ClientWindow.getTextArea().setCaretPosition(ClientWindow.getTextArea().getDocument().getLength());
 		    		 }
 		    		 
 		    	 }
 		    	 
 		    	 
 		     }
 		     }catch(Exception e){
 		    	 
 		     }
 			}

 			
 		 public void ViewconnectedClients(String line) throws IOException {
 		    	 ClientEngine.onlineUsersList.clear();
 		    	 StringTokenizer s= new StringTokenizer(line,":");
 		    	 String user;
 		    	 user=s.nextToken();
 		    	
 		    	 int end=Integer.parseInt(s.nextToken());
 		    	for(int i=0;i<end;i++){
 		    		user=s.nextToken();
 		    		StringTokenizer s2= new StringTokenizer(user," ");
 		    		String ID=s2.nextToken();
 		    		
 		    		String Name=s2.nextToken();
 		    		OnlineUsersInformations c= new OnlineUsersInformations(ID, Name);
 		    		ClientEngine.onlineUsersList.add(c);
 		    		
 		    	 }
 		     RefreshOnlineUsersList();
 		    	
 		}
 		    
 		     public void RefreshOnlineUsersList(){
 		    	 ClientWindow.getOnlineUsersList().removeAll();
 		    	 OnlineUsersButtons.setYLocation(6);
 		    	 ClientWindow.repaint();
 		    	 ClientWindow.revalidate();
 		    	 ClientWindow.getOnlineUsersList().repaint();
 		    	 ClientWindow.getOnlineUsersList().revalidate();
 		    	 
 		    	 for (OnlineUsersInformations c : ClientEngine.onlineUsersList) {
 		    		 OnlineUsersButtons user= new OnlineUsersButtons(c);
 		    		 user.addActionListener(this);
 		    		 user.setText(c.getName());
 		    		 user.setName(c.getID());
 		    		 ClientWindow.getOnlineUsersList().add(user);
 		    	
 				}
 		    	 ClientWindow.repaint();
 		    	 ClientWindow.revalidate();
 		    	 ClientWindow.getOnlineUsersList().repaint();
 		    	 ClientWindow.getOnlineUsersList().revalidate();
 			 }
 			
 			public void SendMessage(String DestinationID){
 				 String nothing = "";
 			        if ((ClientWindow.getTextField().getText()).equals(nothing)) {
 			        	ClientWindow.getTextField().setText("");
 			        	ClientWindow.getTextField().requestFocus();
 			        } else {
 			            try {
 			            	ClientWindow.getTextArea().append(ClientWindow.getTextField().getText()+"\n");
 			            	ClientWindow.getTextArea().setCaretPosition(ClientWindow.getTextArea().getDocument().getLength());
 			               dout.writeUTF(Name + ":" + ClientWindow.getTextField().getText()+":"+ "chat" + ":" +DestinationID);
 			            } catch (Exception ex) {
 			                ClientWindow.getTextArea().append("Message was not sent. \n");
 			               ClientWindow.getTextArea().setCaretPosition(ClientWindow.getTextArea().getDocument().getLength());
 			            }
 			            ClientWindow.getTextField().setText("");
 			            ClientWindow.getTextField().requestFocus();
 			        }

 			        ClientWindow.getTextField().setText("");
 			        ClientWindow.getTextField().requestFocus();
 			    }

   public void Disconnect() throws IOException{
	   dout.writeUTF(Name+":has been disconncted from the Server"+":Disconnect"+":"+ClientWindow.getFrmChatapp().getName());
	 ClientWindow.getbtnDisconnect().setText("Connect");
	 ClientWindow.getTextArea().append("you are now Disconnected \n");
	 ClientWindow.getBtnSend().setEnabled(false);
	 ClientWindow.getTextField().setEditable(false);
	 ClientWindow.getTextArea().setEditable(false);
	 ClientWindow.getOnlineUsersList().removeAll();
	 
   }
 			@Override
 			public void actionPerformed(ActionEvent e) {
 			if(e.getSource() instanceof OnlineUsersButtons){
 				OnlineUsersButtons b= (OnlineUsersButtons)e.getSource();
 			     CurrentChatID= b.getName();  // return saved ID in the name of the button
 			     JOptionPane.showMessageDialog(null, "You are now chatting with "+b.getText());
 				
 			}
 				
 			}

 			
 			
 		}

 		
 	
     public static void main(String[] args) throws UnknownHostException, IOException {
		ClientEngine c= new ClientEngine();
	}

     
	
}
