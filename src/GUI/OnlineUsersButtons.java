package GUI;

import java.awt.Font;

import javax.swing.JButton;

import Model.ClientConnected;

import Model.OnlineUsersInformations;

public class OnlineUsersButtons extends JButton {
private OnlineUsersInformations User;
private static int YLocation=6;
public OnlineUsersButtons(OnlineUsersInformations c){
	super();
	User= c;
	YLocation+=50;
	this.setFont(new Font("Arial Black", Font.PLAIN, 14));
	this.setBounds(0, YLocation, 185, 44);
	this.setText(c.getName());
	this.setName(c.getID());
}

public OnlineUsersInformations getUser() {
	return User;
}

public static void setYLocation(int yLocation) {
	YLocation = yLocation;
}




}
