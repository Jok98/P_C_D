import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface S_F_int extends Remote{
	String getPage(String url) throws RemoteException, IOException;
	
}
