

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface S_C_int extends Remote {
	void addUser(int ID) throws RemoteException;
	void removeUser(int ID) throws RemoteException;
	String sendHtml() throws RemoteException;
	String sendUrl() throws RemoteException;
	int getID() throws RemoteException;

}
