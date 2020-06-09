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
	public static void main(String[] Args) throws  ClassNotFoundException, IOException, AlreadyBoundException {
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		System.out.println("Server avviato");
		server = new ServerSocket(PORT);
		
		registry= LocateRegistry.createRegistry(1099);
		host = (Args.length < 1) ? null : Args[0];
		
		
		create_single_server();
		}
	

	
	public static void create_single_server() throws IOException, ClassNotFoundException, AlreadyBoundException {
		
		try {
		      while (true) {
		        Socket socket = server.accept();
		        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		        
			    String user = (String)in.readObject();
			    System.out.println("Ricevuto : "+user);
		        if(user.equals("F")) {
		        	
		        	//registry.bind("FS", f_class);
		        	new F_Slave(registry,host);	
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
		      server.close();
		    }
		
	}
}
