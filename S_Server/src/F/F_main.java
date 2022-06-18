package F;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * 
 * @Author Matteo Moi
 * @Author Alex Rabuffetti
 * 
 * 
 * F_main chiede all'utente in input un {@link #url} dal quale poi S_Master ricaverà una stringa contentente l'HTML.
 * La richiesta di F_main a S_Master viene identificata tramite l'invio di una stringa (ObjectOutputStream) con cui viene inviato anche {@link #url}.
 * 
 * 
 *
 */

public class F_main extends UnicastRemoteObject {
    
    public F_main() throws RemoteException  {
    
    }
    
    static String id ;
    static String html;
    static String html_head;
    static String url;
    static Registry registry;
    /**
     * 
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     * @throws AlreadyBoundException
     * @throws NotBoundException
     * @throws ClassNotFoundException
     */
	public static void main(String[] args) throws IOException, URISyntaxException, AlreadyBoundException, NotBoundException, ClassNotFoundException {
		/*if (System.getSecurityManager() == null) { 
		     System.setSecurityManager(new SecurityManager()); 
		   }*/
		BufferedReader input = new BufferedReader( new InputStreamReader(System.in));
    	System.out.println("Inserire URL del sito desiderato");
    	url = input.readLine();
    	
    	InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, 8080);
		ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());
		
    	out.writeObject("F"+"lll"+url);
    	out.flush();
		out.close();
		System.out.println("Ottenuto html dall' url "+ url);
    	

    }
	


	
}
