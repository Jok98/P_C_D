package S;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/**
 * @Author Matteo Moi
 * @Author Alex Rabuffetti
 * Jacopo Alessi
 * 
 *C_Slave gestisce le richieste da parte dell'app C_Main tramite {@link #reg} autobindando la propria classe che implementa i metodi dell'interfaccia S_C_int.
 *
 *
 *
 */
public class C_Slave extends Thread implements S_C_int{
    static Registry reg;
    static String host;
    static String ID;
    static ArrayList<String> userList = new ArrayList<String>();
    
    public C_Slave(Registry reg, String host, String ID) {
   	 this.reg = reg;
   	 this.host = host;
   	 this.ID = ID;
   
   	 start();
    }
    
    public void run() {
    
   	 
   	 try {
   		 S_C_int obj = new C_Slave(reg, host, ID);
   		 S_C_int C = (S_C_int)UnicastRemoteObject.exportObject(obj, 1099);
   		 reg.rebind("SC", C);
   		 sleep(1000);

   		 
   	 } catch (RemoteException | InterruptedException e) {
   	 
   		 ((Throwable) e).printStackTrace();
   	 }
   	 
    }
    
    /** 
     * {@link #addUser(String)} aggiunge alla lista degli utenti {@link #ID} passato da S_Master. 
     */
   
    @Override
    
    public synchronized void addUser(String ID) throws RemoteException {
   	 	userList.add(ID);
   	 	System.out.println("Aggiunto utente : "+ID);
   	 	System.out.println("Lista : "+ userList);
   	 
    }


    /** 
     * {@link #removeUser(String)} rimuove dalla lista degli utenti {@link #ID} passato da C_main. 
     */   
    @Override
    public synchronized void removeUser(String ID) throws RemoteException {
    	userList.remove(ID);
    	System.out.println("Rimosso utente : "+ ID);
    	System.out.println("Lista : "+ userList);
    }

    /** 
     * {@link #sendHtml()} @return una stringa contenente l'HTML della pagina indicata da F_main.
     */   
    @Override
    public String sendHtml() throws RemoteException {
   	 String html = S_Master.html;
   	 return html;
    }
    
    /** 
     * {@link #sendUrl()} @return una stringa contenente l'URL della pagina indicata da F_main.
     */   
    @Override
    public String sendUrl() throws RemoteException {
   	 String url =S_Master.url;
   	 return url;
    }

    /** 
     * {@link #getID()} @return {@link #ID} da assegnare all'istanza di C_main che fa la richiesta.
     */   
    @Override
    public synchronized String getID() throws RemoteException {
   	
   	 return ID;
    }

    /**
     *  {@link #update()} indica a C_main se F_main ha inviato una nuova pagina HTML a S_Master 
     */
	@Override
	public Boolean update() throws RemoteException {
		if(S_Master.update==true) {
			S_Master.update=false;
			return true;
		}
		return false;
		
	}
}
