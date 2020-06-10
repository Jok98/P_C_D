import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class C_main {
    static int ID;
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
    	//ObjectInputStream obj_in_s = new ObjectInputStream(socket.getInputStream());
      	String host = (args.length < 1) ? null : args[0];
      	Registry registry_S_C = LocateRegistry.getRegistry(host);
      	S_C_int S = (S_C_int) registry_S_C.lookup("SC");    
      	ID = S.getID();
      	S.addUser(ID);
      	System.out.println("ID : "+ID);
    	while(true) {
    		
    		if(S.update()==true) {
    		String html = S.sendHtml();
    		String url = S.sendUrl();
   	 	
   	 	
   	 		System.out.println("url : "+url);
   	 		System.out.println("Html : "+html);
   	 		
    		}
    		
    		}	
    		
   	 		//socket.close();
    	
    	
    }
    
}

