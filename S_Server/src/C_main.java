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


public class C_main {
    static String ID;
    static ArrayList<Integer> userList = new ArrayList<Integer>();
    static ObjectInputStream in;
    static Socket socket;
    static ServerSocket server;
    public C_main() {
   	 
    }
    
    public static void main (String[] args) throws NotBoundException, IOException, ClassNotFoundException, InterruptedException {
    	InetAddress addr = InetAddress.getByName(null);
    	Socket socket = new Socket(addr, 8080);
    	System.out.println("connsesso");
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
    	while(true) {
    		Thread.sleep(10000);
    		if(ran.nextBoolean()==false) {
    			
    			S.removeUser("ID");
    			System.out.println("Utente : "+ID+" Rimosso");
    			System.out.println(userList);
    			
    			break;
    		}
    		if((count==0)|(S.update()==true)) {
    		
    		String url = S.sendUrl();
    		//wait(1000);
    		String html = S.sendHtml();
   	 	
   	 		System.out.println("url : "+url);
   	 		System.out.println("Html : "+html);
   	 		count++;
    		}
    		
    		}	
    		System.exit(1);
    }
    
}

