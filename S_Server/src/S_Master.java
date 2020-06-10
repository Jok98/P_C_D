import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class S_Master {
	public static final int PORT=8080;
	static ServerSocket server;
	static Registry registry;
	static String host;
	static int ID = 0;
	static S_F_int sf;
	static Socket socket;
	public static void main(String[] Args) throws  ClassNotFoundException, IOException, AlreadyBoundException {
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		System.out.println("Server avviato");
		
		
		registry= LocateRegistry.createRegistry(1099);
		host = (Args.length < 1) ? null : Args[0];
		
		server = new ServerSocket(PORT);
		try {
			
		      for (int i = 0; i <4;i++) {
		    	socket  = server.accept();
		        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			    String user = (String)in.readObject();
			    
			    System.out.println("Ricevuto : "+user);
		        if(user.equals("F")) {
		        	
		        	
					in.close();
					//socket.shutdownInput();
					//System.out.print(url);
		        	new F_Slave(registry).start();;	
		        	
		        	System.out.println("nuovo F_Slave creato");
		        	in.close();
		        	
				}else if(user.equals("C")) {
					ID = ID +1;
					new C_Slave(registry,host,ID);
					System.out.println("nuovo C_Slave creato");
					in.close();
					
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
