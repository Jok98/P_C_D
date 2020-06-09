

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class C_main {
	static int ID;
	static ArrayList<Integer> userList = new ArrayList<Integer>();
	public C_main() {
		
		
	}
	
	public static void main (String[] args) throws NotBoundException, IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
	    Socket socket = new Socket(addr, 9999);
	    ObjectInputStream obj_in_s = new ObjectInputStream(socket.getInputStream());
	    ID = (int)obj_in_s.readObject();
	    System.out.print(ID);
	    
		String host = (args.length < 1) ? null : args[0];
		Registry registry_S_C = LocateRegistry.getRegistry(host);
		S_C_int S = (S_C_int) registry_S_C.lookup("CS");
		String html = S.sendHtml();
		String url = S.sendUrl();
		S.addUser(ID);
		System.out.println("ID : "+ID);
		System.out.println("url : "+url);
		System.out.println("Html : "+html);
		//socket.close();
	}
	
}
