package S;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * 
 * @Author Matteo Moi
 * @Author Alex Rabuffetti
 * Jacopo Alessi
 * 
 * La classe S_Master crea un ServerSocket {@link #server} con porta = {@link #PORT}. 
 * Viene creato un {@link #registry} con porta "1099" il quale verrà utilizzato per la comunicazione RMI con le applicazioni C_main e F_main. 
 * Viene creato un nuovo {@link #in} che si occupa della lettura dei messaggi inviati da C_main e F_main e riguardante la tipologia di richiesta (se essa è fatta da F o C).
 * Il ciclo while prevede che S_Master rimanga in attesa della richiesta identificata da (String user).
 * In base al richiedente S_Master la classe va a creare dei thread slave i quali si occuperanno della gestione specifica della richiesta.
 * Entrambi i thread (F_slave e C_Slave) richiedono come argomento il registry in modo che tutte le applicazioni impieghino unicamente quel singolo registro per il binding e il lookup.
 * F_Slave richiede per il suo funzionamento l'URL e l'HTML  ottenuti dall'applicazione F_Main e trasferiti tramite Socket ObjectInputStream.
 * C_Slave richiede un {@link #ID} per identificare ogni C_Main. ID viene inizializzato come int per essere poi incrementato ad ogni ciclo. 
 * 
 * 
 */

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
		/*if (System.getSecurityManager() == null) { 
		     System.setSecurityManager(new SecurityManager()); 
		   }*/
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
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
