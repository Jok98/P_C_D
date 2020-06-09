

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface S_C_int extends Remote {
	ArrayList addUser(int ID) throws RemoteException;
	void removeUser() throws RemoteException;
	void setPosition() throws RemoteException;
	String sendHtml() throws RemoteException;
	String sendUrl() throws RemoteException;
	int getID() throws RemoteException;

}
