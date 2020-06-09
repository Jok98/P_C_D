
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface S_F_int extends Remote{
	String getPage() throws RemoteException;
	String getUrl() throws RemoteException;
}
