

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class F_main extends UnicastRemoteObject implements S_F_int,Serializable {
    
    public F_main() throws RemoteException {
    
    }
    
    static String id = "1";
    static String html;
    static String url;
    static Registry reg;
    
    
    public String getUrl() throws RemoteException {
		
		return url;
	}
    
    public String getPage(){
    	return html;
    }
    public static String getHtml(String url) throws URISyntaxException, UnknownHostException, IOException {

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
    

    

	public static void main(String[] args) throws IOException, URISyntaxException, AlreadyBoundException, NotBoundException {
    	
    	
    	InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, 8080);
		ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());
		String F = "F";
		out.writeObject(F);
		out.flush();
		System.out.println("Inviato : "+F );
		out.close();
    	BufferedReader input = new BufferedReader( new InputStreamReader(System.in));
    	System.out.println("Inserire URL del sito desiderato");
    	url = input.readLine();
    	getHtml(url);
    	
    	System.setProperty("java.rmi.server.hostname","192.168.1.7");
    	F_main f = new F_main();
    	reg = LocateRegistry.getRegistry();
    	reg.bind("FS", f);
    	//System.out.println(reg.REGISTRY_PORT);
    	

    }




	
}
