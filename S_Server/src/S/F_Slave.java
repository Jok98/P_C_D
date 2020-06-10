package S;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
/**
 * 
 * @Author Matteo Moi
 * @Author Alex Rabuffetti
 * Jacopo Alessi
 * 
 * Tramite {@link #registry} ottenuto da S_Master, F_Slave fa un autorebind.
 * F_Slave implementa S_F_int.
 * Viene implementato il metodo {@link #getPage(String)} che tramite un {@link #url} ottiene il codice {@link #html} della pagina indicata dall'applicazione F_Slave.
 * {@link #getPage(String)} @return {@link #html}.
 * 
 */


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
		/*if (System.getSecurityManager() == null) { 
		     System.setSecurityManager(new SecurityManager()); 
		   }*/
		System.out.println("Partito");
		try {
		
			S_F_int obj = new F_Slave(registry);
			S_F_int F = (S_F_int)UnicastRemoteObject.exportObject(obj, 1099);
			registry.rebind("SF",F);
			sleep(1000);
			
			System.out.println("Registro caricato : " + registry.list());
			System.out.println("S_Thread ha ricevuto l'url : " + S_Master.url);
			System.out.println("S_Thread ha ricevuto l'head_html : " +S_Master.html);
			
			
		} catch (IOException | InterruptedException e) {
			
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
