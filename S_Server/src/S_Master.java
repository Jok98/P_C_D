import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;


public class S_Master {
	public static final int PORT=8080;
	static ServerSocket server;
	static Registry registry;
	static String host;
	static String html;
	static int ID = 0;
	static String url;
	static S_F_int sf;
	static Socket socket;
	static ObjectInputStream in;
	public static void main(String[] Args) throws  ClassNotFoundException, IOException, AlreadyBoundException {
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		System.out.println("Server avviato");
		
		String host = (Args.length < 1) ? null : Args[0];
		registry= LocateRegistry.createRegistry(1099);
		
		
		server = new ServerSocket(PORT);
		try {
			
		      for (int i = 0; i <4;i++) {
		    	socket  = server.accept();
		       in  = new ObjectInputStream(socket.getInputStream());
		       @SuppressWarnings("resource")
			Scanner scan = new Scanner((String) in.readObject()).useDelimiter("lll");
			    String user = scan.next();
			    
			  //in.close();
			    System.out.println("Ricevuto : "+user);
		        if(user.equals("F")) {
		        	url = scan.next();
		        	//html = scan.next();
		        	//System.out.println(url);
		        	new F_Slave(registry).start();;	
		        	System.out.println("nuovo F_Slave creato");
		        	in.close();
		        	
				}else if(user.equals("C")) {
					ID = ID +1;
					new C_Slave(registry,host,ID);
					System.out.println("nuovo C_Slave creato");
					//in.close();
					
				}
		    }
		    } finally {
		      socket.close();
		      server.close();
		    }
		
		//create_single_server();
		}
	

	
	public static void create_single_server() throws IOException, ClassNotFoundException, AlreadyBoundException {
		
		
	}
}
