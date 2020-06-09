import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class S_Master {
	public static final int PORT=8080;
	static ServerSocket server;
	static Registry reg;
	static String host;
	public static void main(String[] Args) throws  ClassNotFoundException, IOException {
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		System.out.println("Server avviato");
		server = new ServerSocket(PORT);
		reg= LocateRegistry.createRegistry(1099);
		host = (Args.length < 1) ? null : Args[0];
		create_single_server();
		}
	
	public static void create_single_server() throws IOException, ClassNotFoundException {
		
		try {
		      while (true) {
		        Socket socket = server.accept();
		        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			    String user = (String)in.readObject();
			    System.out.println("Ricevuto : "+user);
		        if(user.equals("F")) {
		        	new F_Slave(reg,host);	
		        	System.out.println("nuovo F_Slave creato");
		        	in.close();
				}else if(user.equals("C")) {
					new C_Slave();
					System.out.println("nuovo C_Slave creato");
					in.close();
				}
		    }
		    } finally {
		      server.close();
		    }
		
	}
}
