package Model;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ClientConnected {
int ID;
String name;
DataOutputStream dout;

public ClientConnected(int ID,String name,DataOutputStream Dout){
	this.ID=ID;
	this.name= name;
	this.dout= dout;
}

public int getID() {
	return ID;
}

public String getName() {
	return name;
}

public DataOutputStream getDout() {
	return dout;
}

}
