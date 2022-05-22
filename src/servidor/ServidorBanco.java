package servidor;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServidorBanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//criar objeto servidor
			ImplBancoRemoto refObjRemoto = new ImplBancoRemoto();
			
			BancoRemoto skeleton = (BancoRemoto) UnicastRemoteObject.exportObject(refObjRemoto, 0);
			
			LocateRegistry.createRegistry(20001);  
			
			System.out.println(InetAddress.getLocalHost().getHostAddress());

			Registry registro = LocateRegistry.getRegistry(
					InetAddress.getLocalHost().getHostAddress(), 
					20001);

			
			registro.bind("Bank", skeleton);

			System.err.println("Servidor pronto:");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
