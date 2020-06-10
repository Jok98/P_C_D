package S;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * @TheBigAuthor jokmoi
 * @pseudoauthor Sir_Pente
 * 
 */
public interface S_C_int extends Remote {
	void addUser(String iD) throws RemoteException;
	void removeUser(String string) throws RemoteException;
	String sendHtml() throws RemoteException;
	String sendUrl() throws RemoteException;
	String getID() throws RemoteException;
	Boolean update() throws RemoteException;
}
