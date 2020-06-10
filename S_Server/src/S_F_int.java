
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface S_F_int extends Remote{
	//String getUrl() throws RemoteException;
	String getPage(String url) throws RemoteException, IOException;
	
}
