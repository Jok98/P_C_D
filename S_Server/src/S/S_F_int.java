package S;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * @TheBigAuthor jokmoi
 * @pseudoauthor Sir_Pente
 * 
 */
public interface S_F_int extends Remote{
	String getPage(String url) throws RemoteException, IOException;
	
}
