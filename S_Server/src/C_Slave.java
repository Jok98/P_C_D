import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
    @Override
    public synchronized void addUser(String ID) throws RemoteException {
   	 userList.add(ID);
   	 System.out.print("Aggiunto utente : "+ID);
   	System.out.println("Lista : "+ userList);
   	 
    }

    @Override
    public synchronized void removeUser(String ID) throws RemoteException {
   	 userList.remove(ID);
   	 System.out.print("Rimosso utente : "+ID);
    }


    @Override
    public String sendHtml() throws RemoteException {
   	 String html = S_Master.html;
   	 return html;
    }

    @Override
    public String sendUrl() throws RemoteException {
   	 String url =S_Master.url;
   	 return url;
    }

    @Override
    public synchronized String getID() throws RemoteException {
   	
   	 return ID;
    }

	@Override
	public Boolean update() throws RemoteException {
		if(S_Master.update==true) {
			S_Master.update=false;
			return true;
		}
		return false;
		
		
	}



}
