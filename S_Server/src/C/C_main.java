package C;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;



/**
 * 
 * @Author Matteo Moi
 * @Author Alex Rabuffetti
 * Jacopo Alessi
 * 
 * Ad ogni avvio di un'istanza di C_main la quale si collega a S_Master (tramite {@link #socket}) che a sua volta va a creare un thread (C_slave) apposito per gestire la richiesta.
 * C_main esegue l'accesso al registry. Viene poi utilizzato un lookup per accedere ai metodi remoti implementati da C_slave.
 * Tramite metodi remoti L'istanza C_main viene inserita in una {@link #userList} dichiarata e contenuta in C_slave che tiene conto delle istanze di C_main che hanno fatto richiesta di abbonamento ad S_Master.
 * Viene generato randomicamente un boolean, che sarà usato come condizione per mantenere o no l'istanza di C_main attiva.
 * All'interno del ciclo se vengono rispettate le condizioni C_main visualizzerà a schermo l'URL e l'HTML inviati da F_main.
 * All'uscita del ciclo è previsto la cancellazione dell'utente dalla {@link #userList} degli abbonati.
 * 
 */
public class C_main {
    static String ID;
    static ArrayList<Integer> userList = new ArrayList<Integer>();
    static ObjectInputStream in;
    static Socket socket;
    static ServerSocket server;
    public C_main() {
   	 
    }
    /**
     * 
     * @param args
     * @throws NotBoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * 
     */
    public static void main (String[] args) throws NotBoundException, IOException, ClassNotFoundException, InterruptedException {
    	/*if (System.getSecurityManager() == null) { 
    	     System.setSecurityManager(new SecurityManager()); 
    	   }*/
    	InetAddress addr = InetAddress.getByName(null);
    	Socket socket = new Socket(addr, 8080);
    	System.out.println("connesso");
    	ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());
    	out.writeObject("C");
    	out.flush();
    	out.close();
      	String host = (args.length < 1) ? null : args[0];
      	Registry registry_S_C = LocateRegistry.getRegistry(host);
      	S_C_int S = (S_C_int) registry_S_C.lookup("SC");    
      	ID = S.getID();
      	S.addUser(ID);
      	System.out.println("Utente iscritto ID : "+ID);
      	Random ran = new Random();
      	
      	int count = 0;
    	do {
    		Thread.sleep(10000);

    		if((count==0)|(S.update()==true)) {
    		
    		String url = S.sendUrl();
    		//wait(1000);
    		String html = S.sendHtml();
   	 	
   	 		System.out.println("url : "+url);
   	 		System.out.println("Html : "+html);
   	 		count++;
    		}
    		
    		}while(ran.nextBoolean()==true);
    		//ID = S.getID();
    		S.removeUser(ID);
    		System.out.println("Utente : "+ID+" Rimosso");
    		System.out.println(userList);
    		System.exit(1);
    }
    
}

