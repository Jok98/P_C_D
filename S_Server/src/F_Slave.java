import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


//usare stub extends UnicastRemoteObject
public class F_Slave extends Thread{
	Registry registry;
	static String url;
	static String html;
	static String host;
	public F_Slave(Registry registry, String host) {
		this.registry= registry;
		this.host=host;
		start();
	}
	
	
	public void run() {
		System.out.println("Partito");
		try {
		
			//System.setProperty("java.rmi.server.hostname","192.168.1.7");
			registry=LocateRegistry.getRegistry(host);
			
			S_F_int F = (S_F_int) registry.lookup("FS");
			url = F.getUrl();
			html = F.getPage();
			
			//System.out.print(url);
			
		} catch (RemoteException | NotBoundException e) {
			
			e.printStackTrace();
		}
		
	}


	
}
