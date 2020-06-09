import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public F_Slave(Registry registry, String host) {
		this.registry= registry;
		this.host=host;

		start();
	}
	
	
	public void run() {
		System.out.println("Partito");
		try {
			
			S_F_int obj = new F_Slave(registry,host);
			
			
			S_F_int F = (S_F_int)UnicastRemoteObject.exportObject(obj, 1099);
			
			registry.rebind("SF",F);
			
			System.out.print("Registro caricato : " + registry.list());
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
	}


	@Override
	public String getUrl() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
