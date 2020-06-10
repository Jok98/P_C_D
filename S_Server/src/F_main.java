import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class F_main extends UnicastRemoteObject implements F_S_int  {
    
    public F_main() throws RemoteException  {
    
    }
    
    static String id ;
    static String html;
    static String html_head;
    static String url;
    static Registry registry;

	public static void main(String[] args) throws IOException, URISyntaxException, AlreadyBoundException, NotBoundException, ClassNotFoundException {
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

	@Override
	public String getHtml() throws RemoteException {
		
		return html_head;
	}

	
}
