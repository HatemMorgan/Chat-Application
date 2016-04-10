package Model;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.crypto.Data;

import GUI.SView;

public class Server4 {
   static final int Port = 4000; // this is the port where the server is listenning to
	SView serverWindow;
	private ArrayList<ClientConnected> ClientsConnectedList;
	private ArrayList<DataOutputStream> doutList;
	private Socket ClientSocket;
	public Server4() throws IOException{

		serverWindow= new SView();
		serverWindow.getTextArea().append("Server Strat.....\n");
		serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
		serverWindow.getTextArea().append("Server is waiting for connection \n");
		serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
		runServer();
	}

	private void runServer() throws IOException{
		ServerSocket serverSocket= new ServerSocket(Port);
		ClientsConnectedList= new ArrayList<ClientConnected>();
		doutList= new ArrayList<DataOutputStream>();
	     while(true){
	    	 ClientSocket= serverSocket.accept();
	    DataInputStream din = new DataInputStream(ClientSocket.getInputStream());
	    DataOutputStream dout= new DataOutputStream(ClientSocket.getOutputStream());
	     Thread ts= new Thread( new ThreadedServer(din,dout));
	     ts.start();
	     }
	}
	
	public static void main(String args[]) throws Exception {
    
		Server1 s= new Server1();
 

	}

	
	
	//---------------------------------------------------------------------------//
	
	
	public class ThreadedServer implements Runnable{

	 private ClientConnected clientConnected;
	private DataInputStream din;
	private DataOutputStream dout;
	 private String messageout;
	 
		public ThreadedServer(DataInputStream din,DataOutputStream dout) throws IOException{
		this.din=din;
		this.dout=dout;
	 
		}
		
		 public void run() 
	     {
	          String Stream;
	          String[] data;

	          try 
	          {
	              while ((Stream = din.readUTF()) != null) 
	              {
	                serverWindow.getTextArea().append("Received: " + Stream + "\n");
	                serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
	                  data = Stream.split(":");
	            

	                  if (data[2].equals("connect")) 
	                  {
	                	  String s=data[0] + ":" + " is now Connected" + ":" + "chat";
	                		
	
	                      userAdd(data[0]);

	                      TellAllUsers(s);
	
	                     // System.out.println("length = "+ServerEngine.this.ClientsConnectedList.size());
	                  } 
	               
	                  else if (data[2].equals("chat")) 
	                  {
	                	  serverWindow.getTextArea().append(Stream+"\n");
	                	  serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
	                	  String message=data[0]+":"+data[1]+":"+data[2];
	                	  SendMessageToDestination(message, data[3]);
	                  } else{
	                	  if(data[2].equals("Disconnect")){
	                		 
	                		  doutList.set(Integer.parseInt(data[3]),null);
	                		  ClientsConnectedList.set(Integer.parseInt(data[3]),null);
	                		  RemoveDisconnectedUser();
	                		  TellAllUsers(data[0]+":"+data[1]+":"+"chat");
	                		  serverWindow.getTextArea().append(data[0]+" is now disconnected \n");
	                	  }
	                  
	                  else 
	                  {
	                      serverWindow.getTextArea().append("No Conditions were met. \n");
	                      serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
	                  }
	                  }
	              } 
	           } 
	           catch (Exception ex) 
	           {
	        	   serverWindow.getTextArea().append("Lost a connection. \n");
	        	   serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
	               
	           } 
		} 
	  

		public void userAdd(String Name) throws IOException{
		
			boolean flag=true;
			for (ClientConnected c: ClientsConnectedList) {
				if(c!=null){
				if(c.getName().equals(Name)){
					flag=false;
					break;
				}
				}
			}

			if(flag){
			int ID = ClientsConnectedList.size();
				ClientConnected clientConnected = new ClientConnected(ID, Name,dout);
				ClientsConnectedList.add(clientConnected);
                doutList.add(dout);
			serverWindow.getTextArea().append(("User " + Name+ " is now connected \n"));
			serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
        
			try {
				//dout.writeUTF("You are now Conncted to the server");
				dout.writeUTF(ID+"");
				ViewconnectedClients(clientConnected);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				serverWindow.getTextArea().append(("User " + Name+ " not connected due Duplicate Names\n"));
				serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
				//dout.writeUTF("You cannot connect to the server Please try again with different name");
				dout.writeUTF("-1");  // determines that the user is not connected 
			}
		
		}

		public  void ViewconnectedClients(ClientConnected  clientConnected) throws IOException {
			String message;
			String s="";
			if(ClientsConnectedList.size()>1){
	          int count=0;
				message = "1:";
				
			for (ClientConnected c : ClientsConnectedList) {
				if(c!=null){
				if (!(c.equals(clientConnected))) {
					
	                   if(c.ID<ClientsConnectedList.size()-1)
					s += c.ID + " " + c.name +":";
				}else{
					s += c.ID + " " + c.name+" ";
				}
				count++;
			}
				
			}
			message+=count+":";
			message+=s;
			TellAllUsers(message);
			
			}
		
		}
		
		public void RemoveDisconnectedUser(){
			String message;
			String s="";
			if(ClientsConnectedList.size()>1){
	          
				message = "1:";
			int count=0;
			for (ClientConnected c : ClientsConnectedList) {
				if(c!=null){
				//if (!(c.equals(clientConnected))) {
					
	                   if(c.ID<ClientsConnectedList.size()-1){
					s += c.ID + " " + c.name +":";
				}else{
					s += c.ID + " " + c.name+" ";
				}
				count++;
			
			}
			}
			message+=count+":";
			message+=s;
			TellAllUsers(message);
			
			}
		}
		
		
	public  void TellAllUsers(String Message){

		try {
		
		for (DataOutputStream d: doutList) {
			if(d!=null){
			
				d.writeUTF(Message);
			}
		}
		} catch (Exception e) {
	serverWindow.getTextArea().append("Error in telling every user \n");
	serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
		}
	}
		
	public void SendMessageToDestination(String message,String DestinationID){

		
		try {
			DataOutputStream d= doutList.get(Integer.parseInt(DestinationID));
			d.writeUTF(message);
	
		} catch (IOException e) {
			serverWindow.getTextArea().append("Message wasnot sent \n");
			serverWindow.getTextArea().setCaretPosition(serverWindow.getTextArea().getDocument().getLength());
			
		}
	}

	}
	
}
