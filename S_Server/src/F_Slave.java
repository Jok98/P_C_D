import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;


//usare stub extends UnicastRemoteObject
public class F_Slave extends Thread implements S_F_int{
	Registry registry;
	static String url;
	static String html;
	static String host;
	static F_S_int fs;
	public F_Slave(Registry registry) {
		this.registry= registry;

		
	}

	
	
	
	public void run() {
		
		System.out.println("Partito");
		try {
		
			S_F_int obj = new F_Slave(registry);
			S_F_int F = (S_F_int)UnicastRemoteObject.exportObject(obj, 1099);
			registry.rebind("SF",F);
			sleep(1000);
			fs = (F_S_int)registry.lookup("FS");
			html = fs.getHtml();
			/*
			 * client
			 */
			
		
			
			
			System.out.println("Registro caricato : " + registry.list());
			System.out.println("S_Thread ha ricevuto l'url : " + S_Master.url);
			System.out.println("S_Thread ha ricevuto l'head : " + html);
			
			
		} catch (IOException | NotBoundException | InterruptedException e) {
			
			e.printStackTrace();
		}
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}




	
	@Override
	public String getPage(String url) throws IOException {
		URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        InputStream tmp = urlConnection.getInputStream();
        html = tmp.toString();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(tmp, "UTF-8")))
            {
                String inputLine;
                StringBuilder stringBuilder = new StringBuilder();
                while ((inputLine = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(inputLine);
                }

                html= stringBuilder.toString();
            }
        
		//System.out.println("Ottenuto html dall' url "+ url);
		
        return html;

	}












	

}
