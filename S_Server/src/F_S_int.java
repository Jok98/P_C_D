import java.rmi.Remote;
import java.rmi.RemoteException;

public interface F_S_int extends Remote{
	String getUrl() throws RemoteException;
}
