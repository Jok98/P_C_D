import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
	static int i;
	static Boolean update;
	public static void main(String[] Args) throws  ClassNotFoundException, IOException, AlreadyBoundException {
		System.setProperty("java.rmi.server.hostname","192.168.1.7");
		System.out.println("Server avviato");
		
		String host = (Args.length < 1) ? null : Args[0];
		registry= LocateRegistry.createRegistry(1099);
		
		
		server = new ServerSocket(PORT);
		try {
			
		      while(true) {
		    	  //update = false;
		    	  socket  = server.accept();
		    	  in  = new ObjectInputStream(socket.getInputStream());
		    	  @SuppressWarnings("resource")
		    	  Scanner scan = new Scanner((String) in.readObject()).useDelimiter("lll");
		    	  
		    	  String user = scan.next();
		    	  
		    	  //in.close();
		    	  System.out.println("Ricevuto : "+user);
		        if(user.equals("F")) {
		        	url = scan.next();
		        	System.out.println("S : Html & Url");
		        	F_Slave f= new F_Slave(registry);
                    f.start();
                    String tmphtml = f.getPage(url);
                    html= tmphtml.substring(0, 40);
		        	update = true;
		        	System.out.println("nuovo F_Slave creato");
		        	in.close();
		        	
				}else if(user.equals("C")) {
					i++;
					String id = Integer.toString(ID +i);
					new C_Slave(registry,host,id);
					System.out.println("nuovo C_Slave creato");
					
				}
		    }
		    } finally {
		    	
		      socket.close();
		      server.close();
		    }

		}
	
}
