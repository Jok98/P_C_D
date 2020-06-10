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


//usare stub extends UnicastRemoteObject
public class F_Slave extends Thread implements S_F_int{
	Registry registry;
	static String url;
	static String html;
	static String host;

	public F_Slave(Registry registry) {
		this.registry= registry;

		
	}

	
	
	
	public void run() {
		
		System.out.println("Partito");
		try {
			
			S_F_int obj = new F_Slave(registry);
			S_F_int F = (S_F_int)UnicastRemoteObject.exportObject(obj, 1099);
			registry.rebind("SF",F);
			
			/*
			 * client
			 */
			/*registry = LocateRegistry.getRegistry();
			F_S_int fs = (F_S_int)registry.lookup("FS");
			url = fs.getUrl();
			System.out.println(url);
			*/
			System.out.println("Registro caricato : " + registry.list());
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public String getUrl() throws RemoteException {
		
		return url;
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
        return html;

	}







	

}
