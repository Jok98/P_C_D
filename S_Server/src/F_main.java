import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


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
    	/*
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		registry = LocateRegistry.getRegistry();
    	//client
    	S_F_int sf = (S_F_int) registry.lookup("SF");
    	F_main f_main = new F_main();
    	registry.rebind("FS", f_main);
    	html = sf.getPage(url);
    	html_head = html.substring(0, 40);
    	System.out.println("Inviato : "+"F"+ " "+ url +" "+html_head );
		*/

    	//server
    	
    	
    	
    	/*
    	out.writeObject(texthtml);
    	out.flush();
    	//out.close();
    	out.writeObject(url);
    	out.flush();
    	*/
    	

    }








	@Override
	public String getHtml() throws RemoteException {
		
		return html_head;
	}


	
	



	




	
}
