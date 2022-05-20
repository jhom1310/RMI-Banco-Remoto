package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BancoRemoto extends Remote {
	
	// método a ser implementado
    Conta logar(String login, String senha) throws RemoteException;
    double sacar(String n_conta, double valor) throws RemoteException;
    double depositar(String n_conta, double valor) throws RemoteException;
    double transferir(String n_conta, String n_contaD, double valor) throws RemoteException;
    double saldo(String n_conta) throws RemoteException;
    ArrayList<Conta> listaContas() throws RemoteException;


}
